/*
 * Created on 2012-11-26
 */
package com.osight.core.service.impl;

import java.util.List;

import com.osight.core.dao.AlbumDAO;
import com.osight.core.pojos.AlbumData;
import com.osight.core.service.AlbumService;
import com.osight.core.type.AlbumAccessType;
import com.osight.framework.service.BaseDbService;

/**
 * @author chenw
 * @version $Id$
 */
public class AlbumServiceImpl extends BaseDbService implements AlbumService {
	private AlbumDAO	albumDAO;

	@Override
	public AlbumData newPublicAlbum(String name, String description) {
		AlbumData data = new AlbumData();
		data.setName(name);
		data.setDescription(description);
		data.setType(AlbumAccessType.PASSWORD);
		return albumDAO.saveOrUpdate(data);
	}

	@Override
	protected void doCreate() {
		albumDAO = (AlbumDAO) getDAO("albumDAO", AlbumDAO.class);
	}

	@Override
	protected void doRemove() {
	}

	@Override
	public AlbumData getAlbumById(long id) {
		return albumDAO.getAlbumById(id);
	}

	@Override
	public List<AlbumData> getAllAlbums() {
		return albumDAO.getAllAlbums();
	}

}
