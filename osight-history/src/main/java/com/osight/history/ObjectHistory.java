/*
 * Created on 2012-12-25
 */
package com.osight.history;

import java.io.Serializable;
import java.util.Calendar;

import com.osight.framework.pojos.AbstractModel;

/**
 * @author chenw
 * @version $Id$
 */
public class ObjectHistory<T extends AbstractModel> implements Serializable, Comparable<ObjectHistory<T>> {

    private static final long serialVersionUID = 1L;

    private String logBy;
    private Calendar logTime;
    private String transactionId;
    private String version;

    T currVersionObject;

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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public T getCurrVersionObject() {
        return currVersionObject;
    }

    public void setCurrVersionObject(T currVersionObject) {
        this.currVersionObject = currVersionObject;
    }

    public int compareTo(ObjectHistory<T> o) {
        return this.logTime.compareTo(o.logTime);
    }

}
