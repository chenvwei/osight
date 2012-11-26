/*
 * Created on 2012-11-26
 */
package com.osight.core.service;

import com.osight.core.pojos.AlbumData;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public interface AlbumService {
    public AlbumData newPublicAlbum(String name, String description);

    public AlbumData getAlbumById(long id);
}
