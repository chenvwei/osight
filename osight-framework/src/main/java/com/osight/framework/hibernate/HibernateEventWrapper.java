/*
 * Created on 2012-12-25
 */
package com.osight.framework.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.FlushEventListener;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;

/**
 * @author chenw
 * @version $Id$
 */
public class HibernateEventWrapper {
    private SessionFactory sessionFactory;

    public HibernateEventWrapper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setPostInsertEvenetListener(PostInsertEventListener listener) {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(listener);
    }

    public void setPostUpdateEvenetListener(PostUpdateEventListener listener) {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(listener);
    }

    public void setPostDeleteEvenetListener(PostDeleteEventListener listener) {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(listener);
    }

    public void setFlushEvenetListener(FlushEventListener listener) {
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.FLUSH).appendListener(listener);
    }
}
