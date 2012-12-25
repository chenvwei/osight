/*
* Created on 2012-12-25
*/
package com.osight.history.listener;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultFlushEventListener;
import org.hibernate.event.spi.FlushEvent;

import com.osight.framework.hibernate.ThreadLocalManager;
import com.osight.framework.util.UUIDUtil;

/**
 * @author chenw
 * @version $Id$
 */
public class VersionIdFlushEventListener extends DefaultFlushEventListener {
    private static final long serialVersionUID = 1L;

    @Override
    public void onFlush(FlushEvent event) throws HibernateException {
        /**
         * 从目前来看，hibernet(3.2)是在flush的时候触发这个事件的
         * 但是也不能采用同一个事务，同一个版本号的做法，因为同一个事务里面可能存在多次flush。
         * 同一个flush采用同一个version可以在不同关系的表中进行关联，如zsxx_info/zsxx_info_detail,如果可能，可以加上一个事务号
         */
        String key = HistoryEventListener.getHistoryThreadResKey(event.getSession());
        
        if(ThreadLocalManager.hasResource(key))
            ThreadLocalManager.unbindResource(key);
        
        String persistedObjectVersion = UUIDUtil.getRandomUUID();
        ThreadLocalManager.bindResource(key , persistedObjectVersion);
        try{
            super.onFlush(event);
        }
        finally{
            ThreadLocalManager.unbindResource(key);
        }
    }
}
