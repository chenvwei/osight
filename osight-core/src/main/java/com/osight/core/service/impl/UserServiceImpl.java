/*
 * Created on 2012-11-19
 */
package com.osight.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osight.core.dao.UserDAO;
import com.osight.core.pojos.UserData;
import com.osight.core.service.UserService;

/**
 * @author chenw
 * @version $Id$
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDao")
	UserDAO	userDAO;

	@Override
	public UserData getUserData(String id) {
		return userDAO.getUserData(id);
	}

	@Override
	public UserData createUser(String nickName, String email) {
		UserData user = getUserByEmail(email);
		if (user == null) {
			user = new UserData();
			user.setEmail(email);
		}
		user.setNickName(nickName);
		return userDAO.saveOrUpdate(user);
	}

	@Override
	public UserData createUser(String nickName, String email, String userName, String password) {
		UserData user = getUserByEmail(email);
		if (user == null) {
			user = getUserByUserName(userName);
			if (user == null) {
				user = new UserData();
				user.setNickName(nickName);
				user.setEmail(email);
				user.setUserName(userName);
				user.setPassword(password);
				user.setStatus(true);
				return userDAO.saveOrUpdate(user);
			}
		}
		return null;
	}

	@Override
	public UserData getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	@Override
	public UserData getUserByUserName(String userName) {
		return userDAO.getUserByUserName(userName);
	}

}
