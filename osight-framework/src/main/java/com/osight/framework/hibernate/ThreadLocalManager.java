/*
 * Created on 2012-11-16
 */
package com.osight.framework.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw 
 * @version $Id$
 */
@SuppressWarnings("rawtypes")
public class ThreadLocalManager {
    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalManager.class);

    private static ThreadLocal resources = new ThreadLocal() {
        protected Object initialValue() {
            return new HashMap();
        }
    };

    public static Map getResourceMap() {
        return (Map) resources.get();
    }

    public static Object getResource(Object key) {
        Object value = getResourceMap().get(key);
        if (value != null && logger.isDebugEnabled()) {
            logger.debug("Retrieved value [" + value + "] for key [" + key + "] bound to thread ["
                    + Thread.currentThread().getName() + "]");
        }
        return value;
    }

    public static boolean hasResource(Object key) {
        return getResourceMap().containsKey(key);
    }

    @SuppressWarnings("unchecked")
    public static void bindResource(Object key, Object value) throws IllegalStateException {
        if (hasResource(key)) {
            throw new IllegalStateException("Already a value for key [" + key + "] bound to thread");
        }
        getResourceMap().put(key, value);
        if (logger.isDebugEnabled()) {
            logger.debug("Bound value [" + value + "] for key [" + key + "] to thread [" + Thread.currentThread().getName()
                    + "]");
        }
    }

    public static Object unbindResource(Object key) throws IllegalStateException {
        if (!hasResource(key)) {
            throw new IllegalStateException("No value for key [" + key + "] bound to thread");
        }
        Object value = getResourceMap().remove(key);
        if (logger.isDebugEnabled()) {
            logger.debug("Removed value [" + value + "] for key [" + key + "] from thread ["
                    + Thread.currentThread().getName() + "]");
        }
        return value;
    }
}
