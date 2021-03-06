/*
 * Created on 2012-11-22
 */
package com.osight.core.service;

import com.osight.core.Constants;
import com.osight.framework.service.spring.SpringBeanLocator;

/**
 * @author chenw
 * @version $Id$
 */
public class LotteryServiceFactory {
    public static LotteryService getLotteryService() {
        Object bean = SpringBeanLocator.getInstance(Constants.SPRING_CONFIG_FILE).getBean("lotteryService");
        return (LotteryService) bean;
    }
}
