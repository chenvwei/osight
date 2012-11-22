/*
 * Created on 2012-11-19
 */
package com.osight.framework.hibernate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.framework.exception.DAOException;
import com.osight.framework.transaction.LocalHibernateTransactionController;
import com.osight.framework.transaction.SessionsInTransactionHolder;
import com.osight.framework.transaction.TransactionIdHelper;


/**
 * @author chenw 
 * @version $Id$
 */
public class HibernateSession {
    private static final Logger logger = LoggerFactory.getLogger(HibernateSession.class);

    /** The constants for describing the ownerships **/
    private static final Owner trueOwner = new Owner(true);
    private static final Owner fakeOwner = new Owner(false);

    /**
     * 在事务结束的时候自动关闭
     * 
     * @param sessionFactory
     */
    public static void createTxManagedSession(SessionFactory sessionFactory) throws HibernateException {
        SessionHolder sessionHolder = getSessionHolder(sessionFactory);
        if (sessionHolder != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Session Found - Give a Fake identity");
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("No Session Found - Create and give the identity");
            }
            Session session = null;
            try {
                session = sessionFactory.openSession();
            } catch (HibernateException e) {
                throw new DAOException("Create session error!", e);
            }
            sessionHolder = new SessionHolder(session);
            bindSessionHolder(sessionFactory, sessionHolder);

            prepareSession(sessionFactory);

            TransactionIdHelper.bindTranactionIdIfNoExists();
        }
    }

    private static SessionHolder getSessionHolder(SessionFactory sessionFactory) {
        String key = getSessionFactoryBindKey(sessionFactory);
        SessionHolder sessionHolder = (SessionHolder) ThreadLocalManager.getResource(key);
        return sessionHolder;
    }

    private static void bindSessionHolder(SessionFactory sessionFactory, SessionHolder sessionHolder) {
        String key = getSessionFactoryBindKey(sessionFactory);
        ThreadLocalManager.bindResource(key, sessionHolder);
    }

    /**
     * 在TransactionSynch中调用,不要在程序中直接调用
     * 
     * @param sessionFactory
     * @param ownership
     */
    protected static void closeTxManagedSession(SessionFactory sessionFactory) {
        if (logger.isDebugEnabled()) {
            logger.debug("Identity is accepted. Now closing the session");
        }

        try {
            Session session = getSession(sessionFactory);
            if (session.isOpen()) {
                try {
                    logger.trace("Closing Session");
                    session.close();
                } catch (Throwable t) {
                    logger.warn("Unable to close session");
                }
            }
        } finally {
            // remove
            ThreadLocalManager.unbindResource(getSessionFactoryBindKey(sessionFactory));
            // 事务结束后的处理
            TransactionIdHelper.unbindTranactionId();
        }
    }

    public static void prepareSession(SessionFactory sessionFactory) throws HibernateException {
        InitialContext context = null;
        try {
            context = new InitialContext();
            TransactionManager tm = (TransactionManager) context.lookup("java:/TransactionManager");
            tm.getTransaction().registerSynchronization(new TransactionSynch(sessionFactory));
        } catch (NamingException e) {
            throw new HibernateException("Unable to locate TransactionManager", e);
        } catch (IllegalStateException e) {
            throw new HibernateException("Unable to register transaction synch", e);
        } catch (RollbackException e) {
            throw new HibernateException("Unable to register transaction synch", e);
        } catch (SystemException e) {
            throw new HibernateException("Unable to locate Transaction", e);
        } finally {
            release(context);
        }
    }

    private static void release(InitialContext ctx) {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (Throwable t) {
                logger.info("Unable to release context", t);
            }
        }
    }

    /**
     * get the hibernate session and set it on the thread local. Returns trueOwner if it actually
     * opens a session
     */
    public static Object createSession(SessionFactory sessionFactory) {
        SessionHolder sessionHolder = getSessionHolder(sessionFactory);
        if (sessionHolder != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Session Found - Give a Fake identity");
            }
            return fakeOwner;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("No Session Found - Create and give the identity");
            }
            Session session = null;
            try {
                session = sessionFactory.openSession();
            } catch (Exception e) {
                if (null != session) {
                    logger.error("Close session on exception");
                    try {
                        session.close();
                    } catch (Exception e2) {
                    }
                }
                throw new DAOException("Create session error!", e);
            }

            boolean hasException = true;
            try {
                sessionHolder = new SessionHolder(session);
                bindSessionHolder(sessionFactory, sessionHolder);

                SessionsInTransactionHolder sessionsInTransaction = LocalHibernateTransactionController
                        .getTransactionHolder();
                if (null != sessionsInTransaction) {
                    // hibernate此时去获取jdbc的连接，因此在连接池满的情况下，此操作可能会抛出异常。
                    session.beginTransaction();
                    sessionsInTransaction.addSession(session);
                } else {
                    // 自动提交事务，多个事务可能存在交叉
                    TransactionIdHelper.bindTranactionIdIfNoExists();
                }

                hasException = false;
            } finally {
                if (hasException) {// 存在异常
                    // 取消绑定，以免影响后续线程。否则由于没有清理ThreadLocal,后续线程在调用createSession时会一直返回fakeOwner
                    // 从而导致事务无法提交
                    unbind(sessionFactory);
                    if (null != session) {
                        logger.error("Close session on exception");
                        try {
                            session.close();
                        } catch (Exception e2) {
                        }
                    }
                }
            }

            return trueOwner;
        }
    }

    /**
     * The method for closing a session. The close and flush will be executed only if the session is
     * actually created by this owner.
     */
    public static void closeSession(SessionFactory sessionFactory, Object ownership) {
        closeSession(sessionFactory, ownership, true);
    }

    public static void closeSession(SessionFactory sessionFactory, Object ownership, boolean flush) {
        if (((Owner) ownership).identity) {
            if (logger.isDebugEnabled()) {
                logger.debug("Identity is accepted. Now closing the session");
            }

            SessionsInTransactionHolder transaction = LocalHibernateTransactionController.getTransactionHolder();
            if (null == transaction) {
                // 不存在事务控制
                Session session = getSession(sessionFactory);
                boolean hasError = false;
                try {
                    if (flush) {
                        try {
                            session.flush();
                        } catch (HibernateException e) {
                            logger.error("Flush session error!", e);
                            hasError = true;
                        }
                    }

                    try {
                        session.close();
                    } catch (HibernateException e) {
                        logger.error("Close session error!", e);
                        hasError = true;
                    }
                } finally {
                    // remove
                    unbind(sessionFactory);
                }

                if (hasError)
                    throw new DAOException("关闭session时报错");
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Identity is rejected. Ignoring the request");
            }
        }
    }

    protected static String getSessionFactoryBindKey(SessionFactory sessionFactory) {
        return sessionFactory.toString();
    }

    public static void unbind(SessionFactory sessionFactory) {
        if (null == sessionFactory) {
            Throwable e = new Throwable();
            logger.error("sessionFactoy为null", e);
            return;
        }

        String key = getSessionFactoryBindKey(sessionFactory);

        ThreadLocalManager.unbindResource(key);
        TransactionIdHelper.unbindTranactionId();
    }

    public static Session getSession(SessionFactory sessionFactory, boolean allowCreate) {
        SessionHolder sessionHolder = getSessionHolder(sessionFactory);
        if (sessionHolder != null) {
            return sessionHolder.getSession();
        }

        if (allowCreate) {
            Session session = null;
            try {
                session = sessionFactory.openSession();
            } catch (HibernateException e) {
                throw new DAOException("Create session error!", e);
            }
            sessionHolder = new SessionHolder(session);
            bindSessionHolder(sessionFactory, sessionHolder);
            return session;
        } else {
            throw new DAOException("Create session not allowed!");
        }
    }

    /**
     * 返回当前session，如果不存在，说明没有按照规定的调用方式调用 returns the current session
     */
    public static Session getSession(SessionFactory sessionFactory) {
        return getSession(sessionFactory, false);
    }

    /**
     * Internal class , for handling the identity. Hidden for the developers
     */
    protected static class Owner {
        public Owner(boolean identity) {
            this.identity = identity;
        }

        boolean identity = false;
    }
}
