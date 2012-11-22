/*
 * Created on 2012-11-19
 */
package com.osight.framework.hibernate;

import java.lang.reflect.InvocationHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw 
 * @version $Id$
 */
public abstract class HibernateInvocationHandler implements InvocationHandler {

    protected Logger log = LoggerFactory.getLogger(getClass());
    protected BaseHibernateDAO dao = null;

    public HibernateInvocationHandler() {
    }

    public BaseHibernateDAO getDao() {
        return dao;
    }

    public void setDao(BaseHibernateDAO dao) {
        this.dao = dao;
    }

    public HibernateInvocationHandler(Object dao) {
        this.dao = (BaseHibernateDAO) dao;
    }

}
