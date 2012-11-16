/*
 * Created on 2012-11-16
 */
package com.osight.framework.invoke;

import com.osight.framework.hibernate.ThreadLocalManager;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class InvokeInfoThreadLocalManager {
    public static final String INVOKE_INFO = "invokeInfo#rODnEy87ZhiLIAo";

    public static InvokeInfo getCallInfo() {
        return (InvokeInfo) ThreadLocalManager.getResource(INVOKE_INFO);
    }

    public static void bindCallInfo(InvokeInfo callInfo) {
        if (null == callInfo)
            throw new IllegalArgumentException();
        ThreadLocalManager.bindResource(INVOKE_INFO, callInfo);
    }

    public static void unbind() {
        ThreadLocalManager.unbindResource(INVOKE_INFO);
    }
}
