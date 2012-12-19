/*
 * Created on 2012-11-16
 */
package com.osight.framework.invoke;

import java.io.Serializable;

/**
 * @author chenw 
 * @version $Id$
 */
public class InvokeInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String systemId;
    private String moduleId;
    private String userIp;
    private String serverIp;// 发起调用的服务器IP

    private String userId;// 如果没有登录用户信息，可以放相关模块的识别信息

    private String callId;// 只有在远程方法调用时才有意义

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }
}
