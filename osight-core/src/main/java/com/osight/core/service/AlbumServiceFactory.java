/*
 * Created on 2012-11-26
 */
package com.osight.core.service;

import com.osight.framework.service.spring.SpringBeanLocator;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class AlbumServiceFactory {
    public static AlbumService getAlbumService() {
        Object bean = SpringBeanLocator.getInstance("serviceImpl.xml").getBean("albumService");
        return (AlbumService) bean;
    }
}
