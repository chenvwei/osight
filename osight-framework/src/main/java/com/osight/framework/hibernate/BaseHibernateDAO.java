/*
 * Created on 2012-11-19
 */
package com.osight.framework.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class BaseHibernateDAO {
    protected SessionFactory sessionFactory;
    protected HibernateUtil hibernateUtil;

    public void startTxManaged() throws HibernateException {
        HibernateSession.createTxManagedSession(sessionFactory);
    }

    public Object start() {
        return HibernateSession.createSession(getSessionFactory());
    }

    public void end(Object identity) {
        HibernateSession.closeSession(sessionFactory, identity);
    }

    public Session getSession() {
        return HibernateSession.getSession(sessionFactory);
    }

    /**
     * @return
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param factory
     */
    public void setSessionFactory(SessionFactory factory) {
        sessionFactory = factory;
        hibernateUtil = new HibernateUtil();
        hibernateUtil.setSessionFactory(sessionFactory);
    }

    public HibernateUtil getHibernateUtil() {
        return hibernateUtil;
    }

    public void setHibernateUtil(HibernateUtil util) {
        throw new UnsupportedOperationException("Not supported, it depends on sessionFactory");
    }
}
