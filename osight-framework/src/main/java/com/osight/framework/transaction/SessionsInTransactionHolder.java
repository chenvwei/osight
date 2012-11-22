/*
 * Created on 2012-11-19
 */
package com.osight.framework.transaction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw 
 * @version $Id$
 */
public class SessionsInTransactionHolder {
    private List<Session> sessions = new ArrayList<Session>(2);
    protected Logger log = LoggerFactory.getLogger(getClass());

    public void addSession(Session session) {
        if (null == session) {
            log.error("session为null");
            return;
        }
        if (!sessions.contains(session)) {
            sessions.add(session);
        }
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
