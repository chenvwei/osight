/*
 * Created on 2012-11-16
 */
package com.osight.framework.invoke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenw 
 * @version $Id$
 */
public class InvokeInfoHelper {
    protected static Logger log = LoggerFactory.getLogger(InvokeInfoHelper.class);

    public static InvokeInfo getCallInfo() {
        InvokeInfo callInfo = InvokeInfoThreadLocalManager.getInvokeInfo();
        return callInfo;
    }

    public static String getCurrentUser() {
        InvokeInfo callInfo = InvokeInfoThreadLocalManager.getInvokeInfo();
        if (null == callInfo)
            return "Anonymous";
        else {
            String userId = callInfo.getUserId();
            return null == userId ? "Anonymous" : userId;
        }
    }

    public static String getCurrentUserIp() {
        InvokeInfo callInfo = InvokeInfoThreadLocalManager.getInvokeInfo();
        if (null == callInfo)
            return "Unkown";
        else {
            String ip = callInfo.getUserIp();
            if (null == ip)
                log.warn("userip unkown");
            return null == ip ? "Unkown" : ip;
        }
    }

    public static String getCurrentServerIp() {
        InvokeInfo callInfo = InvokeInfoThreadLocalManager.getInvokeInfo();
        if (null == callInfo)
            return "Unkown";
        else {
            String ip = callInfo.getServerIp();
            return null == ip ? "Unkown" : ip;
        }
    }

    public static String getCurrentModuleId() {
        InvokeInfo callInfo = InvokeInfoThreadLocalManager.getInvokeInfo();
        if (null == callInfo)
            return "Unkown";
        else {
            String mid = callInfo.getModuleId();
            return null == mid ? "Unkown" : mid;
        }

    }

    public static String getCurrentSystemId() {
        InvokeInfo callInfo = InvokeInfoThreadLocalManager.getInvokeInfo();
        if (null == callInfo)
            return "Unkown";
        else {
            String sid = callInfo.getSystemId();
            return null == sid ? "Unkown" : sid;
        }
    }

    public static String getCallId() {
        InvokeInfo callInfo = InvokeInfoThreadLocalManager.getInvokeInfo();
        if (null == callInfo)
            return null;
        else {
            String callId = callInfo.getCallId();
            return callId;
        }
    }
}
