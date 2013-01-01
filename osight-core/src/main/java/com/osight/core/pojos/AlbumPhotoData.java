/*
 * Created on 2012-12-14
 */
package com.osight.core.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.osight.framework.pojos.AuditableObject;

/**
 * @author chenw
 * @version $Id$
 */
@Entity
@Table(name = "album_photo")
@Component
public class AlbumPhotoData extends AuditableObject {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ALBUM_ID", nullable = false)
    private long albumId;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "REALPATH", nullable = false)
    private String realpath;

    @Column(name = "URL", nullable = false)
    private String url;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
