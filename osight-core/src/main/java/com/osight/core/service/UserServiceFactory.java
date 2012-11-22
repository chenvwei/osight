/*
 * Created on 2012-11-19
 */
package com.osight.core.service;

import com.osight.framework.service.spring.SpringBeanLocator;

/**
 * @author chenw 
 * @version $Id$
 */
public class UserServiceFactory {
    public static UserService getUserService() {
        Object bean = SpringBeanLocator.getInstance("serviceImpl.xml").getBean("userService");
        return (UserService) bean;
    }
}
