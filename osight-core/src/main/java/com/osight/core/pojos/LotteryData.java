/*
 * Created on 2012-11-22
 */
package com.osight.core.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.osight.framework.pojos.AuditableObject;

/**
 * @author chenw
 * @version $Id$
 */
@Entity
@Table(name = "lottery")
public class LotteryData extends AuditableObject {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ISSUENO", nullable = false)
    private long issueNo;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "TYPE_ID")
    private int typeId;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(long issueNo) {
        this.issueNo = issueNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
