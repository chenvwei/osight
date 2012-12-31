/*
 * Created on 2012-12-31
 */
package com.osight.framework.service;

import java.io.Serializable;
import java.util.List;

import com.osight.framework.page.Page;
import com.osight.framework.pojos.AbstractModel;

/**
 * @author chenw
 * @version $Id$
 */
public interface IBaseService<M extends AbstractModel, PK extends Serializable> {
    public M save(M model);

    public M saveOrUpdate(M model);

    public M update(M model);

    public void delete(PK id);

    public void deleteObject(M model);

    public M get(PK id);

    public long countAll();

    public List<M> listAll();

    public Page<M> list(int start, int pageSize);

}
