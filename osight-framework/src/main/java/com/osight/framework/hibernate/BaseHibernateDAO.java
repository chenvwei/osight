/*
 * Created on 2012-11-19
 */
package com.osight.framework.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author chenw 
 * @version $Id$
 */
public class BaseHibernateDAO {
    protected Logger log = LoggerFactory.getLogger(getClass());
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
    @Autowired
    @Qualifier("sessionFactory")
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
