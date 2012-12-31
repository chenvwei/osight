/*
 * Created on 2012-12-25
 */
package com.osight.history.pojo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.osight.framework.pojos.AbstractModel;
import com.osight.history.vo.HistoryInfo;

/**
 * @author chenw
 * @version $Id$
 */
@Entity
@Table(name = "history_info")
public class HistoryLogData extends AbstractModel {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "LOGBY")
    private String logBy;
    @Column(name = "LOGUSERIP")
    private String logUserIp;
    @Column(name = "LOGTIME")
    private Calendar logTime;
    @Column(name = "OPTYPE")
    private String operationType;// insert update delete

    @Column(name = "TRANSACTIONID")
    private String transactionId;

    @Column(name = "VERINFO")
    private String version;// 版本号。版本号的控制原则上在于应用程序。

    @Column(name = "TNAME")
    private String tableName;
    @Column(name = "FNAME")
    private String fieldName;
    @Column(name = "PKEY")
    private long primaryKey;
    @Column(name = "OV")
    private String oldValue;
    @Column(name = "NV")
    private String newValue;

    public void convertFrom(HistoryInfo info) {
        this.logBy = info.getLogBy();
        this.logTime = info.getLogTime();
        this.operationType = info.getOperationType();
        this.tableName = info.getTableName();
        this.fieldName = info.getFieldName();
        this.primaryKey = info.getPrimaryKey();
        this.oldValue = toString(info.getOldValue());
        this.newValue = toString(info.getNewValue());
        this.version = info.getVersion();
        this.transactionId = info.getTransactionId();
        this.logUserIp = info.getLogUserIp();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getLogBy() {
        return logBy;
    }

    public void setLogBy(String logBy) {
        this.logBy = logBy;
    }

    public Calendar getLogTime() {
        return logTime;
    }

    public void setLogTime(Calendar logTime) {
        this.logTime = logTime;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
   
    public long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getLogUserIp() {
        return logUserIp;
    }

    public void setLogUserIp(String logUserIp) {
        this.logUserIp = logUserIp;
    }

    @Override
    public String toString() {
        return String.format("id=%s,logBy=%s,logUserIp=%s,logTime=%d,newValue=%s,oldValue=%s,operationType=%s,"
                + "primaryKey=%s,tableName=%s,filedName=%s", id, logBy, logUserIp, logTime.getTimeInMillis(), newValue,
                oldValue, operationType, primaryKey, tableName, fieldName);
    }

    public static String toString(Object o) {
        if (null == o) {
            return null;
        } else if (o instanceof Calendar) {
            return "" + ((Calendar) o).getTimeInMillis();
        } else if (o instanceof Date) {
            return "" + ((Date) o).getTime();
        }

        return o.toString();
    }

    public static Object toObject(Class<?> c, String str) {
        if (null == str)
            return null;

        if (c == Calendar.class) {
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(Long.parseLong(str));
            return time;
        } else if (c == int.class) {
            return Integer.parseInt(str);
        } else if (c == Integer.class) {
            return new Integer(Integer.parseInt(str));
        } else if (c == Long.class) {
            return new Long(Long.parseLong(str));
        } else if (c == BigDecimal.class) {
            return new BigDecimal(str);
        }

        return str;
    }

    @Override
    public long getId() {

        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
