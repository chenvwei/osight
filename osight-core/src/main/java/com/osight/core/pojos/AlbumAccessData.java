/*
 * Created on 2012-11-26
 */
package com.osight.core.pojos;

import com.osight.core.type.AlbumAccessType;
import com.osight.framework.pojos.AuditableObject;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class AlbumAccessData extends AuditableObject {
    private static final long serialVersionUID = 1L;
    private long id;
    private long albumId;
    private AlbumAccessType type;
    private String question;
    private String answer;
    private String password;

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

    public AlbumAccessType getType() {
        return type;
    }

    public void setType(AlbumAccessType type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
