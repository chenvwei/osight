/*
 * Created on 2012-11-19
 */
package com.osight.framework.service.spring;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenw 
 * @version $Id$
 */
public class SpringBeanLocator {
    private static Map<String, SpringBeanLocator> beanFactoryMap = new HashMap<String, SpringBeanLocator>();
    private ClassPathXmlApplicationContext beanFac;
    protected Logger log = LoggerFactory.getLogger(getClass());

    private SpringBeanLocator(ClassPathXmlApplicationContext beanFac) {
        this.beanFac = beanFac;
    }

    public synchronized static SpringBeanLocator getInstance(String confFileClasspath) {
        SpringBeanLocator l = beanFactoryMap.get(confFileClasspath);
        if (null == l) {
            ClassPathXmlApplicationContext beanFac = new ClassPathXmlApplicationContext(confFileClasspath);
            l = new SpringBeanLocator(beanFac);
            beanFactoryMap.put(confFileClasspath, l);
        }
        return l;
    }
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName) {
        Object bean = beanFac.getBean(beanName);
        try {
            T castBean = (T) bean;
            return castBean;
        } catch (ClassCastException e) {
            log.error("Class cast error,beanName[]" + beanName, e);
            return null;
        }
    }
}
