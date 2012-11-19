/*
 * Created on 2012-11-19
 */
package com.osight.framework.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextJndiBeanFactoryLocator;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

import com.osight.framework.hibernate.HibernateInvocationHandler;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class BeanLocator {
    protected HibernateInvocationHandler daoInvocationHandler;
    protected Logger log = LoggerFactory.getLogger(getClass());

    public static final String BEAN_FACTORY_PATH_ENVIRONMENT_KEY = "java:comp/env/ejb/BeanFactoryPath";

    /**
     * Helper strategy that knows how to locate a Spring BeanFactory (or ApplicationContext).
     */
    private BeanFactoryLocator beanFactoryLocator;

    /** factoryKey to be used with BeanFactoryLocator */
    private String beanFactoryLocatorKey = "servicelayer-context";
    /** Spring BeanFactory that provides the namespace for this service */
    private BeanFactoryReference beanFactoryReference;

    private String factoryLocatorSelector = null;

    private String dbProviderName;

    public DbProvider getDbProvider() {
        return (DbProvider) getBean(dbProviderName);
    }

    public Object getBean(String bean) {
        return getBeanFactory().getBean(bean);
    }

    protected BeanFactory getBeanFactory() {
        return this.beanFactoryReference.getFactory();
    }

    /**
     * Load a Spring BeanFactory namespace. Subclasses must invoke this method.
     * <p>
     * Package-visible as it shouldn't be called directly by user-created subclasses.
     * 
     */
    void loadBeanFactory() throws BeansException {
        if (this.beanFactoryLocator == null) {
            this.beanFactoryLocator = new ContextJndiBeanFactoryLocator();
        }
        if (this.beanFactoryLocatorKey == null) {
            this.beanFactoryLocatorKey = BEAN_FACTORY_PATH_ENVIRONMENT_KEY;
        }
        this.beanFactoryReference = this.beanFactoryLocator.useBeanFactory(this.beanFactoryLocatorKey);
    }

    /**
     * Unload the Spring BeanFactory instance. The default ejbRemove method invokes this method, but
     * subclasses which override ejbRemove must invoke this method themselves.
     * <p>
     * Package-visible as it shouldn't be called directly by user-created subclasses.
     */
    void unloadBeanFactory() throws FatalBeanException {
        if (this.beanFactoryReference != null) {
            this.beanFactoryReference.release();
            this.beanFactoryReference = null;
        }
    }

    public void init() {
        String selector = getFactoryLocatorSelector();
        if (StringUtils.isEmpty(selector))
            setBeanFactoryLocator(ContextSingletonBeanFactoryLocator.getInstance());
        else
            setBeanFactoryLocator(ContextSingletonBeanFactoryLocator.getInstance(selector));

        loadBeanFactory();
    }

    public void setBeanFactoryLocator(BeanFactoryLocator beanFactoryLocator) {
        this.beanFactoryLocator = beanFactoryLocator;
    }

    /**
     * 
     * @return
     */
    public String getFactoryLocatorSelector() {
        return factoryLocatorSelector;
    }

    /**
     * 
     * @return
     */
    public String getBeanFactoryLocatorKey() {
        return beanFactoryLocatorKey;
    }

    public void setBeanFactoryLocatorKey(String beanFactoryLocatorKey) {
        this.beanFactoryLocatorKey = beanFactoryLocatorKey;
    }

    public void setFactoryLocatorSelector(String factoryLocatorSelector) {
        this.factoryLocatorSelector = factoryLocatorSelector;
    }

    public String getDbProviderName() {
        return dbProviderName;
    }

    public void setDbProviderName(String dbProviderName) {
        this.dbProviderName = dbProviderName;
    }
}
