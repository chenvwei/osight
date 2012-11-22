/*
 * Created on 2012-11-19
 */
package com.osight.framework.hibernate;

import org.hibernate.Session;

/**
 * @author chenw 
 * @version $Id$
 */
public class SessionHolder {
    private final Session session;

    public SessionHolder(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
