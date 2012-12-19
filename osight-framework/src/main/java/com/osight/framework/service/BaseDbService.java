/*
 * Created on 2012-11-19
 */
package com.osight.framework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw
 * @version $Id$
 */
public abstract class BaseDbService implements IService {
    /**
     * 数据服务提供者
     */
    protected DbProvider dbProvider;
    private BeanLocator locator;
    protected Logger log = LoggerFactory.getLogger(getClass());

    public void create() {
        if (null == locator) {
            throw new IllegalArgumentException("没有配置locator属性");
        }

        dbProvider = locator.getDbProvider();
        dbProvider.setBeanLocator(locator);
        dbProvider.onCreate();
        doCreate();
    }

    protected abstract void doCreate();

    public void remove() {
        dbProvider.onRemove();
        doRemove();
        locator.unloadBeanFactory();
    }

    protected abstract void doRemove();

    public void setLocator(BeanLocator locator) {
        this.locator = locator;
    }

    public <T> T getDAO(String daoName, Class<T> type) {
        return dbProvider.getDAO(daoName, type);
    }

    public Object getService(String serviceName) {
        return dbProvider.getService(serviceName);
    }

}
