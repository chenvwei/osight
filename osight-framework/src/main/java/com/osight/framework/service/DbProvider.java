/*
 * Created on 2012-11-19
 */
package com.osight.framework.service;


/**
 * 为service对象提供数据库相关的服务
 * 
 * @author chenw 
 * @version $Id$
 */
public interface DbProvider {
    public void setDaoInvocationHander(String invocationHandle);

    public <T> T getDAO(String daoName, Class<T> type);

    /**
     * 获取一个普通的bean
     * 
     * @param serviceName
     * @return
     */
    public Object getService(String serviceName);

    public BeanLocator getBeanLocator();

    public void setBeanLocator(BeanLocator l);

    public void onCreate();

    public void onRemove();
}
