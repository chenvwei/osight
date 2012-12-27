/*
 * Created on 2012-12-27
 */
package com.osight.framework.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenw
 * @version $Id$
 */
public class URLUtil {
    public final static String getRequestURL(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Cannot take null parameters.");
        }

        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();

        String requestUri = (String) request.getAttribute("javax.servlet.forward.request_uri");
        requestUri = (requestUri == null) ? request.getRequestURI() : requestUri;

        StringBuffer buffer = new StringBuffer();
        buffer.append(scheme);
        buffer.append("://");
        buffer.append(serverName);

        if (!(scheme.equalsIgnoreCase("http") && serverPort == 80)
                && !(scheme.equalsIgnoreCase("https") && serverPort == 443)) {
            buffer.append(":");
            buffer.append(String.valueOf(serverPort));
        }

        buffer.append(requestUri);

        return buffer.toString();
    }
}
