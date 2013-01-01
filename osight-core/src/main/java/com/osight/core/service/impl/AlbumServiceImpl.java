/*
 * Created on 2012-11-26
 */
package com.osight.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osight.core.dao.AlbumDAO;
import com.osight.core.pojos.AlbumData;
import com.osight.core.pojos.AlbumPhotoData;
import com.osight.core.service.AlbumService;
import com.osight.core.type.AlbumAccessType;

/**
 * @author chenw
 * @version $Id$
 */
@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
	@Autowired
	@Qualifier("albumDao")
    private AlbumDAO albumDAO;

    @Override
    public AlbumData newPublicAlbum(String name, String description) {
        AlbumData data = new AlbumData();
        data.setName(name);
        data.setDescription(description);
        data.setType(AlbumAccessType.PASSWORD);
        return albumDAO.saveOrUpdate(data);
    }

    @Override
    public AlbumData getAlbumById(long id) {
        return albumDAO.getAlbumById(id);
    }

    @Override
    public List<AlbumData> getAllAlbums() {
        return albumDAO.getAllAlbums();
    }

    @Override
    public AlbumPhotoData newPhoto(long albumId, String realPath, String description) {
        AlbumPhotoData data = new AlbumPhotoData();
        data.setAlbumId(albumId);
        data.setRealpath(realPath);
        data.setUrl("static.osight.com/" + realPath.substring(realPath.indexOf("images")));
        data.setDescription(description);
        return albumDAO.saveOrUpdae(data);
    }

}
