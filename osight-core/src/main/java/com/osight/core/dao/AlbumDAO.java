/*
 * Created on 2012-11-26
 */
package com.osight.core.dao;

import com.osight.core.pojos.AlbumData;

/**
 * @author chenw
 * @version $Id$
 */
public interface AlbumDAO {
    public AlbumData saveOrUpdate(AlbumData data);

    public AlbumData getAlbumById(long id);
}
