/*
 * Created on 2012-11-19
 */
package com.osight.framework.service;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public interface IService extends ServiceWithLifestyle {
    <T> T getDAO(String daoName, Class<T> type);
}
