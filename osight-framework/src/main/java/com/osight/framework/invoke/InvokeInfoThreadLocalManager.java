/*
 * Created on 2012-11-16
 */
package com.osight.framework.invoke;

import com.osight.framework.hibernate.ThreadLocalManager;

/**
 * @author chenw 
 * @version $Id$
 */
public class InvokeInfoThreadLocalManager {
    public static final String INVOKE_INFO = "invokeInfo#rODnEy87ZhiLIAo";

    public static InvokeInfo getInvokeInfo() {
        return (InvokeInfo) ThreadLocalManager.getResource(INVOKE_INFO);
    }

    public static void bindInvokeInfo(InvokeInfo invokeInfo) {
        if (null == invokeInfo)
            throw new IllegalArgumentException();
        ThreadLocalManager.bindResource(INVOKE_INFO, invokeInfo);
    }

    public static void unbind() {
        ThreadLocalManager.unbindResource(INVOKE_INFO);
    }
}
