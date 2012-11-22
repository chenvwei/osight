/*
 * Created on 2012-11-19
 */
package com.osight.framework.transaction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.framework.exception.DAOException;
import com.osight.framework.hibernate.HibernateSession;
import com.osight.framework.hibernate.ThreadLocalManager;

/**
 * @author chenw 
 * @version $Id$
 */
public class LocalHibernateTransactionController {
    private final static String KEY = "sessionsHolder_#!$@)ijeTE";
    protected static Logger log = LoggerFactory.getLogger(LocalHibernateTransactionController.class);

    public static SessionsInTransactionHolder getTransactionHolder() {
        return (SessionsInTransactionHolder) ThreadLocalManager.getResource(KEY);
    }

    /**
     * 启动事务
     */
    public static void beginTransaction() {
        SessionsInTransactionHolder holder = getTransactionHolder();
        if (null != holder) {
            log.error("事务没有清理干净");
            unbindSessions();
        }

        holder = new SessionsInTransactionHolder();
        ThreadLocalManager.bindResource(KEY, holder);
        TransactionIdHelper.bindTranactionId();
    }

    public static void commit(boolean commit) {
        try {
            doCommit(commit);
        } finally {
            unbindSessions();
        }
    }

    private static void unbindSessions() {
        ThreadLocalManager.unbindResource(KEY);
    }

    /**
     * 提交事务
     * 
     * @param commit
     *            是否提交
     */
    private static void doCommit(boolean commit) {
        SessionsInTransactionHolder holder = getTransactionHolder();
        if ((null == holder) || (0 == holder.getSessions().size())) {
            if (null == holder) {
                if (log.isErrorEnabled()) {
                    Throwable toTrace = new Throwable();
                    log.error("没有对应的事务，下面的异常信息只是为了记录调用位置.", toTrace);
                }
            } else {
                if (log.isDebugEnabled()) {
                    Throwable toTrace = new Throwable();
                    log.debug("没有数据库操作，因此没有对应的事务。记录调用堆栈", toTrace);
                }
                TransactionIdHelper.unbindTranactionId();
            }
        } else {
            boolean hasError = false;
            if (commit) {
                // 由于Hibernate listener的存在，使得此过程变得复杂。
                // 在事务提交的时候，可能存在下面的情况：
                // holder.getSessions()中只有应用程序对应的session(appSession)。但是调用appSession.flush的时候，此时触发listener，从而在holder.getSessions()
                // 中加入一个session(listenerSession)
                if (log.isDebugEnabled())
                    log.debug("准备提交事务");
                List<Session> sessions = new ArrayList<Session>(holder.getSessions().size());
                sessions.addAll(holder.getSessions());

                for (Session session : sessions) {
                    if (!session.isOpen()) {
                        log.error("session已经关闭");
                    } else {
                        try {
                            session.flush();
                        } catch (Throwable e) {
                            // 不直接抛出异常时为了每一个session对应的finally里面的语句都能得到执行
                            log.error("Flush session error!", e);
                            hasError = true;
                        }
                    }
                }

                // 由于可能存在Listener，此处需要对listener对于的session进行flush。
                for (Session session : holder.getSessions()) {
                    SessionFactory sessionFactory = null;
                    try {
                        sessionFactory = session.getSessionFactory();
                        if (!session.isOpen()) {
                            log.error("session已经关闭");
                        } else {
                            try {
                                try {
                                    if (session.isDirty())
                                        session.flush();
                                } catch (Throwable e) {
                                    log.error("Flush session error!", e);
                                    hasError = true;
                                }
                                try {
                                    session.getTransaction().commit();
                                    if (log.isDebugEnabled())
                                        log.debug("事务提交完毕");
                                } catch (Throwable e) {
                                    log.error("Commit session error!", e);
                                    hasError = true;
                                }

                                try {
                                    session.close();
                                    if (log.isDebugEnabled())
                                        log.debug("关闭session");
                                } catch (Throwable e) {
                                    log.error("Close session error!", e);
                                    hasError = true;
                                }
                            } catch (Throwable e) {
                                // 不直接抛出异常时为了每一个session对应的finally里面的语句都能得到执行
                                log.error("结束事务时出错", e);
                                hasError = true;
                            }
                        }
                    } finally {
                        HibernateSession.unbind(sessionFactory);
                    }
                }
            } else {
                if (log.isDebugEnabled())
                    log.debug("准备回滚事务");
                for (Session session : holder.getSessions()) {
                    SessionFactory sessionFactory = null;
                    try {
                        sessionFactory = session.getSessionFactory();
                        if (!session.isOpen()) {
                            log.error("session已经关闭");
                        } else {
                            try {
                                try {
                                    session.getTransaction().rollback();
                                    if (log.isDebugEnabled())
                                        log.debug("事务回滚完毕");
                                } catch (Throwable e) {
                                    log.error("Rollback session error!", e);
                                    hasError = true;
                                }

                                try {
                                    session.close();
                                    if (log.isDebugEnabled())
                                        log.debug("关闭session");
                                } catch (Exception e) {
                                    log.error("Close session error!", e);
                                    hasError = true;
                                }
                            } catch (Throwable e) {
                                // 不直接抛出异常时为了每一个session对应的finally里面的语句都能得到执行
                                log.error("结束事务时出错", e);
                                hasError = true;
                            }
                        }
                    } finally {
                        HibernateSession.unbind(sessionFactory);
                    }
                }
            }

            if (hasError) {
                if (commit) {// 无法提交事务，则回滚
                    if (holder.getSessions().size() > 1)
                        // 这个情况非常少，因为往往在flush的时候报错
                        log.error("多个sessionFactory时事务出错，可能存在事务不一致。");
                    else
                        log.error("事务提交出错，进行回滚。");

                    for (Session session : holder.getSessions()) {
                        if (!session.isOpen()) {
                            log.error("session已经关闭");
                            continue;
                        }

                        try {
                            session.getTransaction().rollback();
                        } catch (Throwable e) {
                            log.error("Rollback session error!", e);
                        }

                        try {
                            session.close();
                        } catch (Throwable e) {
                            log.error("Close session error!", e);
                        }
                    }
                }

                throw new DAOException("结束事务时出错");
            }
        }
    }
}
