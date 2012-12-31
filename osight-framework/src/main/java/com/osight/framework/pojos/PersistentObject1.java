/*
 * Created on 2012-11-16
 */
package com.osight.framework.pojos;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw <a href="mailto:rodneytt@sina.com">chen wei</a>
 * @version $Id$
 */
public abstract class PersistentObject1 implements Serializable {
    private static final long serialVersionUID = 1L;

    protected static transient Logger log = LoggerFactory.getLogger(PersistentObject1.class);

    public PersistentObject1() {
    }

    public abstract long getId();

    public abstract void setId(long id);

    public void setData(PersistentObject1 vo) {
        this.copyProperties(vo);
    }

    public void copyProperties(PersistentObject1 origin) {
        try {
            ConvertUtils.register(new DoubleConverter(null), Double.class);
            ConvertUtils.register(new IntegerConverter(null), Integer.class);
            ConvertUtils.register(new LongConverter(null), Long.class);
            ConvertUtils.register(new BooleanConverter(null), Boolean.class);

            BeanUtils.copyProperties(this, origin);
        } catch (IllegalAccessException e) {
            log.error("copyProperties failed", e);
            throw new IllegalArgumentException("拷贝属性失败");
        } catch (InvocationTargetException e) {
            log.error("copyProperties failed", e);
            throw new IllegalArgumentException("拷贝属性失败");
        }
    }
}
