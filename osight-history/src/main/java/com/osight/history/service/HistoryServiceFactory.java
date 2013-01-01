/*
 * Created on 2012-12-25
 */
package com.osight.history.service;

import com.osight.framework.service.spring.SpringBeanLocator;

/**
 * @author chenw
 * @version $Id$
 */
public class HistoryServiceFactory {
    public static HistoryService getHistoryService() {
        return SpringBeanLocator.getInstance("spring-config-history.xml").getBean("historyService");
    }
}
