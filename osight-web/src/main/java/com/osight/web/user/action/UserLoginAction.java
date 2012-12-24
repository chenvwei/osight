/*
 * Created on 2012-12-24
 */
package com.osight.web.user.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.osight.core.Constants;
import com.osight.core.pojos.UserData;
import com.osight.core.service.UserService;
import com.osight.framework.struts2.BasicSupportAction;

/**
 * @author chenw
 * @version $Id$
 */
public class UserLoginAction extends BasicSupportAction {
    private static final long serialVersionUID = 1L;
    private String loginName;
    private String password;

    private UserService userService;
    UserData user;
    private String from;

    @Override
    public String execute() throws Exception {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.put(Constants.SESSION_USER, user);
        return SUCCESS;
    }

    @Override
    public void validate() {
        user = userService.getUserByEmail(loginName);
        if (user == null) {
            user = userService.getUserByUserName(loginName);
        }
        if (user == null) {
            addActionError("用户不存在！");
        } else {
            if (!user.isPasswordEqual(password)) {
                addActionError("用户名或密码输入错误！");
            }
        }

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
