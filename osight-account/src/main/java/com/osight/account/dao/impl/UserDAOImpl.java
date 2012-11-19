/*
 * Created on 2012-11-19
 */
package com.osight.account.dao.impl;

import com.osight.account.dao.UserDAO;
import com.osight.account.pojos.UserData;
import com.osight.framework.hibernate.BaseHibernateDAO;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class UserDAOImpl extends BaseHibernateDAO implements UserDAO {

    @Override
    public UserData getUserData(String id) {
        return (UserData) hibernateUtil.getObject(id, UserData.class);
    }

}
