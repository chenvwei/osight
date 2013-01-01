/*
 * Created on 2012-11-19
 */
package com.osight.core.service;

import com.osight.core.Constants;
import com.osight.framework.service.spring.SpringBeanLocator;

/**
 * @author chenw
 * @version $Id$
 */
public class UserServiceFactory {
	public static UserService getUserService() {
		Object bean = SpringBeanLocator.getInstance(Constants.SPRING_CONFIG_FILE).getBean("userService");
		return (UserService) bean;
	}
}
