/*
 * Created on 2012-12-31
 */
package com.osight.framework.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.osight.framework.dao.IBaseDAO;
import com.osight.framework.page.Page;
import com.osight.framework.page.PageUtil;
import com.osight.framework.pojos.AbstractModel;

/**
 * @author chenw
 * @version $Id$
 */
public abstract class BaseHibernateDAO<M extends AbstractModel, PK extends Serializable> implements IBaseDAO<M, PK> {
    protected static final Logger log = LoggerFactory.getLogger(BaseHibernateDAO.class);

    private final Class<M> entityClass;
    private final String HQL_LIST_ALL;
    private final String HQL_COUNT_ALL;
    private String pkName = null;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
        // 事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public BaseHibernateDAO() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = this.entityClass.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
            }
        }
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
    }

    @SuppressWarnings("unchecked")
    @Override
    public PK save(M model) {
        return (PK) getSession().save(model);
    }

    @Override
    public void saveOrUpdate(M model) {
        getSession().saveOrUpdate(model);
    }

    @Override
    public void update(M model) {
        getSession().update(model);
    }

    @Override
    public void delete(PK id) {
        getSession().delete(get(id));
    }

    @Override
    public void deleteObject(M model) {
        getSession().delete(model);
    }

    @SuppressWarnings("unchecked")
    @Override
    public M get(PK id) {
        return (M) getSession().get(entityClass, id);
    }

    @Override
    public boolean exists(PK id) {
        return get(id) != null;
    }

    @Override
    public long countAll() {
        Long total = aggregate(HQL_COUNT_ALL);
        return total.longValue();
    }

    @Override
    public List<M> listAll() {
        return list(HQL_LIST_ALL, -1, -1);
    }

    @Override
    public Page<M> list(int start, int pageSize) {
        List<M> list = list(HQL_LIST_ALL, start, pageSize);
        Page<M> page = PageUtil.getPage(list.iterator(), start, pageSize, countAll());
        return page;
    }
    
    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }


    @SuppressWarnings("unchecked")
    protected <T> T aggregate(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        return (T) query.uniqueResult();
    }

    protected void setParameters(Query query, Object[] paramlist) {
        if (paramlist != null) {
            for (int i = 0; i < paramlist.length; i++) {
                if (paramlist[i] instanceof Date) {
                    query.setTimestamp(i, (Date) paramlist[i]);
                } else {
                    query.setParameter(i, paramlist[i]);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> list(final String hql, final int start, final int pageSize, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        if (start > -1) {
            query.setFirstResult(start);
        }
        if (pageSize > -1) {
            query.setMaxResults(pageSize);
        }
        List<T> results = query.list();
        return results;
    }
}
