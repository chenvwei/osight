package com.osight.web.interceptor;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.osight.core.Constants;

public class LoginInterceptor extends AbstractInterceptor {
	private static final long	serialVersionUID	= 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		String needLogin = (String) ctx.getParameters().get("needLogin");
		if (StringUtils.equalsIgnoreCase(needLogin, "false")) {
			return invocation.invoke();
		}
		Map<String, Object> session = ctx.getSession();
		if (session.get(Constants.SESSION_USER) != null) {
			return invocation.invoke();
		}
		ctx.put("tip", "您还没有登录！");
		return Action.LOGIN;
	}

}
