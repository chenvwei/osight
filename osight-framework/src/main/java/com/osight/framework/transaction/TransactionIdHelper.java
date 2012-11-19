/*
 * Created on 2012-11-19
 */
package com.osight.framework.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.framework.hibernate.ThreadLocalManager;
import com.osight.framework.util.UUIDUtil;


/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class TransactionIdHelper {
    private static final String KEY_TRANID = "tranactionId#@#Gsw$903";
    private static final Logger log = LoggerFactory.getLogger(TransactionIdHelper.class);

    /**
     * 绑定一个代表此次事务的uuid
     * 
     * @return
     */
    public static void bindTranactionId() {
        if (ThreadLocalManager.hasResource(KEY_TRANID)) {
            if (log.isWarnEnabled())
                log.warn("transactionId已存在");
            ThreadLocalManager.unbindResource(KEY_TRANID);
        }

        String tranId = UUIDUtil.getRandomUUID();
        ThreadLocalManager.bindResource(KEY_TRANID, tranId);
    }

    public static void bindTranactionIdIfNoExists() {
        if (!ThreadLocalManager.hasResource(KEY_TRANID)) {
            String tranId = UUIDUtil.getRandomUUID();
            ThreadLocalManager.bindResource(KEY_TRANID, tranId);
        } else {
            if (log.isDebugEnabled())
                log.debug("transactionId绑定已经存在");
        }
    }

    public static void unbindTranactionId() {
        if (ThreadLocalManager.hasResource(KEY_TRANID))
            ThreadLocalManager.unbindResource(KEY_TRANID);
        else {
            if (log.isDebugEnabled())
                // 多个sessionFactory时存在这种情况
                log.debug("transactionId已经被取消绑定");
        }
    }

    public static String getTranactionId() {
        String transId = (String) ThreadLocalManager.getResource(KEY_TRANID);
        if (null == transId) {// 自动提交的事务
            transId = UUIDUtil.getRandomUUID();
            if (log.isDebugEnabled())
                log.debug("自动提交事务，产生一个新的transactionId:" + transId);
        }
        return (String) transId;
    }
}
