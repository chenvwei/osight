/*
 * Created on 2012-12-21
 */
package com.osight.framework.web.filter;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chenw
 * @version $Id$
 */
public class DefaultBindInvokeInfoFilter extends BindInvokeInfoFilter {

    String hostName;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        super.init(arg0);
        try {
            InetAddress inet = InetAddress.getLocalHost();
            hostName = inet.getHostName();
        } catch (UnknownHostException e) {
            log.error("can not get the hostname", e);
        }
    }

    @Override
    public String getServerIp(HttpServletRequest request) {
        return hostName;
    }

    @Override
    public String getUserIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @Override
    public String getUserId(HttpServletRequest request) {
        return null;
    }
}
