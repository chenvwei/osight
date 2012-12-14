/*
 * Created on 2012-11-26
 */
package com.osight.core.service;

import java.util.List;

import com.osight.core.pojos.AlbumData;
import com.osight.core.pojos.AlbumPhotoData;

/**
 * @author chenw
 * @version $Id$
 */
public interface AlbumService {
    public AlbumData newPublicAlbum(String name, String description);

    public AlbumData getAlbumById(long id);
    
    public List<AlbumData> getAllAlbums();
    
    public AlbumPhotoData newPhoto(long albumId,String realPath,String description);
}
