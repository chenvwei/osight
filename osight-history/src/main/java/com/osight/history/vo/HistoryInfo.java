/*
 * Created on 2012-12-25
 */
package com.osight.history.vo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author chenw
 * @version $Id$
 */
public class HistoryInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    private String logBy;

    private Calendar logTime;

    private String logUserIp;

    private String operationType;// insert update delete

    private String transactionId;// 事务的uuid标识，需要程序进行设置才行，因此这个值并不是所有时候都有的，所以下面的version不能少。

    private String version;// 版本号。版本号的控制原则上在于应用程序。

    private String tableName;

    private String fieldName;

    private long primaryKey;

    private Object oldValue;

    private Object newValue;

    /**
     * 每个字段的修改历史是否创建一个单独的表进行存放
     */
    private boolean oneTablePerField = false;

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

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
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

    public boolean isOneTablePerField() {
        return oneTablePerField;
    }

    public void setOneTablePerField(boolean oneTablePerField) {
        this.oneTablePerField = oneTablePerField;
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
        return String.format("logBy=%s,logUserIp=%s,logTime=%d,newValue=%s,oldValue=%s,operationType=%s,"
                + "primaryKey=%s,tableName=%s,filedName=%s", logBy, logUserIp, logTime.getTimeInMillis(), newValue,
                oldValue, operationType, primaryKey, tableName, fieldName);
    }
}
