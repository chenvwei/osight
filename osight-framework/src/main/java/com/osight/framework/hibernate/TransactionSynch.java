/*
 * Created on 2012-11-19
 */
package com.osight.framework.hibernate;

import javax.transaction.Synchronization;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.framework.exception.DAOException;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class TransactionSynch implements Synchronization {

    private static Logger log = LoggerFactory.getLogger(TransactionSynch.class);

    SessionFactory sessionFactory = null;

    public TransactionSynch(SessionFactory sessionFactory) {
        if (null == sessionFactory) {
            throw new IllegalArgumentException("SessionFactory to synchronize cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    public void beforeCompletion() {
        Session session = HibernateSession.getSession(sessionFactory);
        if (!session.isOpen()) {
            log.warn("Session already closed");
        } else {
            try {
                log.trace("Flushing Session");
                session.flush();
            } catch (Throwable t) {
                log.warn("Error flushing session");
                throw new DAOException("Error flushing session", t);
            }
        }
    }

    public void afterCompletion(int arg0) {
        HibernateSession.closeTxManagedSession(sessionFactory);
    }

}
