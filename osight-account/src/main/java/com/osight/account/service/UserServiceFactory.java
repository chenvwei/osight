/*
 * Created on 2012-11-19
 */
package com.osight.account.service;

import com.osight.framework.service.spring.SpringBeanLocator;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class UserServiceFactory {
    public static UserService getAcademeServiceLocal() {
        Object bean = SpringBeanLocator.getInstance("serviceImpl.xml").getBean("userService");
        return (UserService) bean;
    }
}
