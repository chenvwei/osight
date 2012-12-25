/*
 * Created on 2012-11-19
 */
package com.osight.core.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.osight.core.Constants;
import com.osight.framework.pojos.AuditableObject;
import com.osight.framework.util.DigestUtil;
import com.osight.framework.util.UUIDUtil;
import com.osight.history.annotation.HistoryProp;

/**
 * @author chenw
 * @version $Id$
 */
@Entity
@Table(name = "user")
@HistoryProp
public class UserData extends AuditableObject {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "password", length = 32)
    private String password;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "salt")
    private String salt;

    @Override
    public long getId() {

        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (StringUtils.length(password) == 32) {
            this.password = password;
        } else {
            this.salt = UUIDUtil.getRandomUUID();
            this.password = DigestUtil.MD5(String.format("%s%s%s", salt, password, Constants.MD5_SALT));
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Transient
    public boolean isPasswordEqual(String password) {
        if (StringUtils.length(password) != 32) {
            password = DigestUtil.MD5(String.format("%s%s%s", this.salt, password, Constants.MD5_SALT));
        }
        return StringUtils.equals(password, this.password);
    }
}
