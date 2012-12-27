package com.osight.core.util;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.osight.core.Constants;
import com.osight.core.pojos.UserData;
import com.osight.framework.util.URLUtil;

public class WebAppUtil {
    public static UserData getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserData user = (UserData) session.getAttribute(Constants.SESSION_USER);
        return user;
    }

    public static boolean isUserLogin(HttpServletRequest request) {
        UserData user = getLoginUser(request);
        return user != null;
    }

    @SuppressWarnings("unchecked")
    public static String getRequestUrl(HttpServletRequest request, String[] excludeParams) throws Exception {
        String actionURLStr = URLUtil.getRequestURL(request);
        StringBuffer strUrl = new StringBuffer(actionURLStr);
        Enumeration<String> parameters = request.getParameterNames();
        boolean isFirstParam = true;
        boolean isExclude;
        while (parameters.hasMoreElements()) {
            isExclude = false;
            String paramName = (String) parameters.nextElement();
            if (excludeParams != null && excludeParams.length > 0) {
                for (String ep : excludeParams) {
                    if (StringUtils.equalsIgnoreCase(paramName, ep)) {
                        isExclude = true;
                        break;
                    }
                }
            }
            if (!isExclude) {
                String value = request.getParameter(paramName);
                if (!StringUtils.isEmpty(value)) {
                    if (isFirstParam) {
                        strUrl.append("?");
                        strUrl.append(paramName);
                        isFirstParam = false;
                    } else {
                        strUrl.append("&" + paramName);
                    }
                    strUrl.append("=" + URLEncoder.encode(value, "UTF-8"));
                }
            }
        }
        return strUrl.toString();
    }
}
