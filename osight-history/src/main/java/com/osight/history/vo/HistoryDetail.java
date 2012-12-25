/*
 * Created on 2012-12-25
 */
package com.osight.history.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author chenw
 * @version $Id$
 */
public class HistoryDetail implements Serializable, Comparable<HistoryDetail> {
    private static final long serialVersionUID = 1L;

    private String logBy;
    private Calendar logTime;
    private String transactionId;
    private String version;
    private String operationType;

    // 属性
    private List<String> names = new ArrayList<String>();
    private List<String> newValues = new ArrayList<String>();
    private List<String> oldValue = new ArrayList<String>();

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getNewValues() {
        return newValues;
    }

    public void setNewValues(List<String> newValues) {
        this.newValues = newValues;
    }

    public List<String> getOldValue() {
        return oldValue;
    }

    public void setOldValue(List<String> oldValue) {
        this.oldValue = oldValue;
    }

    public int compareTo(HistoryDetail o) {
        return this.logTime.compareTo(o.getLogTime());
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
