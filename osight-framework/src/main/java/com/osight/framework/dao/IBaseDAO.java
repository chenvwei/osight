/*
 * Created on 2012-12-31
 */
package com.osight.framework.dao;

import java.io.Serializable;
import java.util.List;

import com.osight.framework.page.Page;
import com.osight.framework.pojos.AbstractModel;

/**
 * @author chenw
 * @version $Id$
 */
public interface IBaseDAO<M extends AbstractModel, PK extends Serializable> {
    public PK save(M model);

    public void saveOrUpdate(M model);

    public void update(M model);

    public void delete(PK id);

    public void deleteObject(M model);

    public M get(PK id);

    public long countAll();

    public List<M> listAll();

    public Page<M> list(int start, int pageSize);

    boolean exists(PK id);

    public void flush();

    public void clear();
}
