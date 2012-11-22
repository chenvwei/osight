/*
 * Created on 2012-11-19
 */
package com.osight.framework.service;

import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.framework.exception.DAOException;
import com.osight.framework.hibernate.BaseHibernateDAO;
import com.osight.framework.hibernate.HibernateInvocationHandler;

/**
 * @author chenw 
 * @version $Id$
 */
public class HibernateDbProvider implements DbProvider {
    protected HibernateInvocationHandler daoInvocationHandler;
    protected Logger log = LoggerFactory.getLogger(getClass());
    BeanLocator locator;

    @SuppressWarnings("unchecked")
    public <T> T getDAO(String daoName, Class<T> type) {
        BaseHibernateDAO dao = (BaseHibernateDAO) locator.getBean(daoName);
        try {
            HibernateInvocationHandler daoHandler = daoInvocationHandler.getClass().newInstance();
            daoHandler.setDao(dao);
            return (T) Proxy
                    .newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] {type}, daoHandler);
        } catch (Exception e) {
            throw new DAOException("Get dao error!", e);
        }
    }

    @SuppressWarnings("rawtypes")
    public void setDaoInvocationHander(String invocationHandle) {
        Class c = null;
        try {
            c = Class.forName(invocationHandle);
        } catch (ClassNotFoundException e) {
            if (log.isErrorEnabled())
                log.error(String.format("Class %s not found", invocationHandle), e);
        }
        try {
            this.daoInvocationHandler = (HibernateInvocationHandler) c.newInstance();
        } catch (InstantiationException e) {
            if (log.isErrorEnabled())
                log.error(String.format("Instantiation Class %s failed", invocationHandle), e);
        } catch (IllegalAccessException e) {
            if (log.isErrorEnabled())
                log.error(String.format("IllegalAccess of Class %s", invocationHandle), e);
        }
    }

    public BeanLocator getBeanLocator() {
        return locator;
    }

    public void setBeanLocator(BeanLocator l) {
        locator = l;
    }

    public void onCreate() {

    }

    public void onRemove() {

    }

    public Object getService(String beanName) {
        return locator.getBean(beanName);
    }

}
