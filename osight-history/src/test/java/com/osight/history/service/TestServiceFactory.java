/*
 * Created on 2012-12-25
 */
package com.osight.history.service;

import com.osight.framework.service.spring.SpringBeanLocator;

/**
 * @author chenw
 * @version $Id$
 */
public class TestServiceFactory {
    public static TestPojoService getService() {
        Object bean = SpringBeanLocator.getInstance("spring-config-history-test.xml").getBean("testPojoService");
        return (TestPojoService) bean;
    }
}
