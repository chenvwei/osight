/*
 * Created on 2012-11-26
 */
package com.osight.core.dao;

import java.util.List;

import com.osight.core.pojos.AlbumData;
import com.osight.core.pojos.AlbumPhotoData;

/**
 * @author chenw
 * @version $Id$
 */
public interface AlbumDAO {
    public AlbumData saveOrUpdate(AlbumData data);
    
    public AlbumPhotoData saveOrUpdae(AlbumPhotoData data);

    public AlbumData getAlbumById(long id);
    
    public List<AlbumData> getAllAlbums();
}
