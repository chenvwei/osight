/*
 * Created on 2012-11-26
 */
package com.osight.core.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.osight.core.type.AlbumAccessType;
import com.osight.framework.pojos.AuditableObject;

/**
 * @author chenw
 * @version $Id$
 */
@Entity
@Table(name = "album")
public class AlbumData extends AuditableObject {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "ACCESS_TYPE", nullable = false)
    private AlbumAccessType type;

    @Column(name = "COVER", nullable = true)
    private long cover;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AlbumAccessType getType() {
        return type;
    }

    public void setType(AlbumAccessType type) {
        this.type = type;
    }

    public long getCover() {
        return cover;
    }

    public void setCover(long cover) {
        this.cover = cover;
    }

}
