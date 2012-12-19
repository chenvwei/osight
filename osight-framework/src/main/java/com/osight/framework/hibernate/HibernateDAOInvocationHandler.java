/*
 * Created on 2012-11-19
 */
package com.osight.framework.hibernate;

import java.lang.reflect.Method;

/**
 * @author chenw 
 * @version $Id$
 */
public class HibernateDAOInvocationHandler extends HibernateInvocationHandler {

    public HibernateDAOInvocationHandler(Object dao) {
        this.dao = (BaseHibernateDAO) dao;
    }

    public HibernateDAOInvocationHandler(BaseHibernateDAO dao) {
        this.dao = dao;
    }

    public HibernateDAOInvocationHandler() {

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        Object identity = null;
        try {
            identity = dao.start();
            result = method.invoke(dao, args);
        } finally {
            if (null != identity)// 连接池满的情况下，identity为null
                dao.end(identity);
        }
        return result;
    }
}
