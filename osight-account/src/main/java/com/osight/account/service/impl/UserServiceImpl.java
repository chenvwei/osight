/*
 * Created on 2012-11-19
 */
package com.osight.account.service.impl;

import com.osight.account.dao.UserDAO;
import com.osight.account.pojos.UserData;
import com.osight.account.service.UserService;
import com.osight.framework.service.BaseDbService;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class UserServiceImpl extends BaseDbService implements UserService {
    UserDAO userDAO;

    @Override
    public UserData getUserData(String id) {
        return userDAO.getUserData(id);
    }

    @Override
    protected void doCreate() {
        userDAO = (UserDAO) getDAO("userDAO", UserDAO.class);
    }

    @Override
    protected void doRemove() {
    }

}
