package com.osight.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.osight.core.Constants;
import com.osight.core.pojos.UserData;

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
}
