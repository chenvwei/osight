package com.osight.web.album.action;

import java.util.List;

import com.osight.core.pojos.AlbumData;
import com.osight.core.service.AlbumService;
import com.osight.core.type.AlbumAccessType;
import com.osight.framework.struts2.BasicSupportAction;

public class AlbumAction extends BasicSupportAction {
	private static final long	serialVersionUID	= 1L;
	private AlbumService		albumService;
	private AlbumData			album;
	private List<AlbumData>		albumList;

	public String add() {
		if (album.getType() == AlbumAccessType.PUBLIC) {
			album = albumService.newPublicAlbum(album.getName(), album.getDescription());
		}
		return list();
	}

	public String list() {
		albumList = albumService.getAllAlbums();
		return "list";
	}
}
