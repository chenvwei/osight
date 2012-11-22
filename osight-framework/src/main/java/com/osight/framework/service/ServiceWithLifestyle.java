/*
 * Created on 2012-11-19
 */
package com.osight.framework.service;

/**
 * @author chenw 
 * @version $Id$
 */
public interface ServiceWithLifestyle {
    /**
     * 创建服务时需调用的方法
     */
    void create();

    /**
     * 删除服务时需调用的方法
     */
    void remove();
}
