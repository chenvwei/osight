/*
 * Created on 2012-11-26
 */
package com.osight.core.dao.impl;

import com.osight.core.dao.AlbumDAO;
import com.osight.core.pojos.AlbumData;
import com.osight.framework.hibernate.BaseHibernateDAO;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class AlbumDAOImpl extends BaseHibernateDAO implements AlbumDAO {

    @Override
    public AlbumData saveOrUpdate(AlbumData data) {
        if (data.getId() == 0) {
            hibernateUtil.save(data);
        } else {
            hibernateUtil.update(data);
        }
        return data;
    }

    @Override
    public AlbumData getAlbumById(long id) {
        return (AlbumData) hibernateUtil.getObject(id, AlbumData.class);
    }

}
