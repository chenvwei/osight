/*
 * Created on 2012-12-31
 */
package com.osight.framework.service.impl;

import java.io.Serializable;
import java.util.List;

import com.osight.framework.dao.IBaseDAO;
import com.osight.framework.page.Page;
import com.osight.framework.pojos.AbstractModel;
import com.osight.framework.service.IBaseService;

/**
 * @author chenw
 * @version $Id$
 */
public abstract class BaseService<M extends AbstractModel, PK extends Serializable> implements IBaseService<M, PK> {
    protected IBaseDAO<M, PK> baseDao;

    public abstract void setBaseDao(IBaseDAO<M, PK> baseDao);

    @Override
    public M save(M model) {
        baseDao.save(model);
        return model;
    }

    @Override
    public M saveOrUpdate(M model) {
        baseDao.saveOrUpdate(model);
        return model;
    }

    @Override
    public M update(M model) {
        baseDao.update(model);
        return model;
    }

    @Override
    public void delete(PK id) {
        baseDao.delete(id);
    }

    @Override
    public void deleteObject(M model) {
        baseDao.deleteObject(model);
    }

    @Override
    public M get(PK id) {
        return baseDao.get(id);
    }

    @Override
    public long countAll() {
        return baseDao.countAll();
    }

    @Override
    public List<M> listAll() {
        return baseDao.listAll();
    }

    @Override
    public Page<M> list(int start, int pageSize) {
        return baseDao.list(start, pageSize);
    }

}
