/*
 * Created on 2012-11-16
 */
package com.osight.framework.hibernate;

import java.io.Serializable;
import java.util.Calendar;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.osight.framework.invoke.InvokeInfoHelper;
import com.osight.framework.pojos.Auditable;

/**
 * @author chenw 
 * @version $Id$
 */
public class AuditInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] currentState, String[] propertyNames, Type[] types) {
        boolean updated = false;

        if (entity instanceof Auditable) {
            Auditable a = (Auditable) entity;
            String userid = getCurrentUser();
            String userip = getCurrentUserIp();
            String serverIp = getCurrentServerIp();
            Calendar stamp = Calendar.getInstance();

            boolean auditable = false;
            if (entity instanceof Auditable) {
                auditable = true;
            }

            for (int i = 0; i < propertyNames.length; i++) {
                String propertyName = propertyNames[i];
                updated |= checkCreatedOn(currentState, a, stamp, i, propertyName);
                updated |= checkCreatedBy(currentState, a, userid, i, propertyName);
                updated |= checkCreatedIp(currentState, a, userip, i, propertyName);
                updated |= checkCreatedServer(currentState, a, serverIp, i, propertyName);
                if (auditable) {
                    updated |= checkUpdatedOn(currentState, (Auditable) a, stamp, i, propertyName);
                    updated |= checkUpdatedBy(currentState, (Auditable) a, userid, i, propertyName);
                    updated |= checkUpdatedIp(currentState, (Auditable) a, userip, i, propertyName);
                    updated |= checkUpdatedServer(currentState, (Auditable) a, serverIp, i, propertyName);
                }
            }
        }

        return updated;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        boolean updated = false;
        if (entity instanceof Auditable) {
            Auditable a = (Auditable) entity;
            String userid = getCurrentUser();
            String userip = getCurrentUserIp();
            String serverIp = getCurrentServerIp();
            Calendar stamp = Calendar.getInstance();

            for (int i = 0; i < propertyNames.length; i++) {
                String propertyName = propertyNames[i];
                updated |= checkUpdatedOn(currentState, a, stamp, i, propertyName);
                updated |= checkUpdatedBy(currentState, a, userid, i, propertyName);
                updated |= checkUpdatedIp(currentState, a, userip, i, propertyName);
                updated |= checkUpdatedServer(currentState, a, serverIp, i, propertyName);
            }
        }

        return updated;
    }

    private boolean checkCreatedOn(Object[] currentState, Auditable auditable, Calendar stamp, int idx, String propertyName) {
        if (propertyName.equals("createdOn")) {
            auditable.setCreatedOn(stamp);
            currentState[idx] = stamp;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCreatedBy(Object[] currentState, Auditable auditable, String username, int idx, String propertyName) {
        if (propertyName.equals("createdBy")) {
            auditable.setCreatedBy(username);
            currentState[idx] = username;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUpdatedOn(Object[] currentState, Auditable auditable, Calendar stamp, int idx, String propertyName) {
        if (propertyName.equals("updatedOn")) {
            auditable.setUpdatedOn(stamp);
            currentState[idx] = stamp;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUpdatedBy(Object[] currentState, Auditable auditable, String username, int idx, String propertyName) {
        if (propertyName.equals("updatedBy")) {
            auditable.setUpdatedBy(username);
            currentState[idx] = username;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCreatedIp(Object[] currentState, Auditable auditable, String ip, int idx, String propertyName) {
        if (propertyName.equals("createdIp")) {
            auditable.setCreatedIp(ip);
            currentState[idx] = ip;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUpdatedIp(Object[] currentState, Auditable auditable, String ip, int idx, String propertyName) {
        if (propertyName.equals("updatedIp")) {
            auditable.setUpdatedIp(ip);
            currentState[idx] = ip;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCreatedServer(Object[] currentState, Auditable auditable, String server, int idx,
            String propertyName) {
        if (propertyName.equals("createdServer")) {
            auditable.setCreatedServer(server);
            currentState[idx] = server;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUpdatedServer(Object[] currentState, Auditable auditable, String server, int idx,
            String propertyName) {
        if (propertyName.equals("updatedServer")) {
            auditable.setUpdatedServer(server);
            currentState[idx] = server;
            return true;
        } else {
            return false;
        }
    }

    private String getCurrentUser() {
        return InvokeInfoHelper.getCurrentUser();
    }

    private String getCurrentUserIp() {
        return InvokeInfoHelper.getCurrentUserIp();
    }

    private String getCurrentServerIp() {
        return InvokeInfoHelper.getCurrentServerIp();
    }
}
