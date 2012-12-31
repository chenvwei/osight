/*
 * Created on 2012-11-16
 */
package com.osight.framework.pojos;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author chenw 
 * @version $Id$
 */
@MappedSuperclass
public abstract class AuditableObject extends AbstractModel implements Auditable {
    private static final long serialVersionUID = 1L;
    private String createdBy;
    private String updatedBy;

    private Calendar createdOn;
    private Calendar updatedOn;

    private String createdIp;
    private String updatedIp;

    private String createdServer;
    private String updatedServer;

    @Column(name = "CREATEDBY")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String userid) {
        this.createdBy = userid;
    }

    @Column(name = "UPDATEDBY")
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String userid) {
        this.updatedBy = userid;
    }

    @Column(name = "CREATEDON")
    public Calendar getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Calendar creationTime) {
        this.createdOn = creationTime;
    }

    @Column(name = "UPDATEDON")
    public Calendar getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Calendar updateTime) {
        this.updatedOn = updateTime;
    }

    @Column(name = "CREATEDIP")
    public String getCreatedIp() {
        return createdIp;
    }

    public void setCreatedIp(String ip) {
        this.createdIp = ip;
    }

    @Column(name = "UPDATEDIP")
    public String getUpdatedIp() {
        return updatedIp;
    }

    public void setUpdatedIp(String ip) {
        this.updatedIp = ip;
    }

    @Column(name = "CREATEDSERVER")
    public String getCreatedServer() {
        return createdServer;
    }

    @Column(name = "UPDATEDSERVER")
    public String getUpdatedServer() {
        return updatedServer;
    }

    public void setCreatedServer(String server) {
        this.createdServer = server;
    }

    public void setUpdatedServer(String server) {
        this.updatedServer = server;
    }

}
