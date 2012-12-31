/*
 * Created on 2012-12-31
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
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw
 * @version $Id$
 */
public abstract class AbstractModel implements Serializable {
    private static final long serialVersionUID = 1L;

    protected static transient Logger log = LoggerFactory.getLogger(AbstractModel.class);

    public AbstractModel() {
    }

    public abstract long getId();

    public abstract void setId(long id);

    public void setData(AbstractModel vo) {
        this.copyProperties(vo);
    }

    public void copyProperties(AbstractModel origin) {
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractModel other = (AbstractModel) obj;
        if (this.getId() != other.getId())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)this.getId();
        return result;
    }

}
