/*
 * Created on 2012-11-19
 */
package com.osight.framework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class ServiceConstantLocator {
    protected Logger log = LoggerFactory.getLogger(getClass());
    private XmlBeanFactory beanFac = null;

    private static final String BEAN_FACTORY_LOCATOR_KEY = "servicelayer-context";
    private static final String FACTORY_LOCATOR_SELECTOR = "beanRefContext.xml";
    private static final String DB_PROVIDER = "dbProvider";

    @SuppressWarnings("unchecked")
    public ServiceConstantLocator(Class c) {
        String className = c.getName();
        String resName = "META-INF/service/" + className + ".xml";
        try {
            Resource resource = new ClassPathResource(resName);
            beanFac = new XmlBeanFactory(resource);
        } catch (Exception e) {
            log.warn(String.format("没有发现服务%s的配置文件，采用默认值", className));
        }
    }

    public String getBeanFactoryLocatorKey() {
        if (null == beanFac)
            return BEAN_FACTORY_LOCATOR_KEY;
        else
            return (String) beanFac.getBean("beanFactoryLocatorKey");
    }

    public String getFactoryLocatorSelector() {
        if (null == beanFac)
            return FACTORY_LOCATOR_SELECTOR;
        else
            return (String) beanFac.getBean("factoryLocatorSelector");
    }

    public String getDbProviderName() {
        if (null == beanFac)
            return DB_PROVIDER;
        else
            return (String) beanFac.getBean("dbProvider");
    }
}
