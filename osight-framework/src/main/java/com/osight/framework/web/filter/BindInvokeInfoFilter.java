/*
 * Created on 2012-12-21
 */
package com.osight.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.framework.invoke.InvokeInfo;
import com.osight.framework.invoke.InvokeInfoThreadLocalManager;

/**
 * @author chenw
 * @version $Id$
 */
public abstract class BindInvokeInfoFilter implements Filter {
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        String userId = getUserId(request);
        String userIp = getUserIp(request);

        String serverIp = getServerIp(request);

        String callId = getCallId(request);

        InvokeInfo invokeInfo = new InvokeInfo();
        invokeInfo.setUserId(userId);
        invokeInfo.setUserIp(userIp);
        invokeInfo.setServerIp(serverIp);
        if (null != callId)
            invokeInfo.setCallId(callId);

        InvokeInfoThreadLocalManager.bindInvokeInfo(invokeInfo);
        try {
            chain.doFilter(req, resp);
        } finally {
            InvokeInfoThreadLocalManager.unbind();
        }
    }

    @Override
    public void destroy() {
    }

    public String getCallId(HttpServletRequest request) {
        return null;
    }

    abstract public String getUserId(HttpServletRequest request);

    abstract public String getUserIp(HttpServletRequest request);

    abstract public String getServerIp(HttpServletRequest request);

}
