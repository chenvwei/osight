/*
 * Created on 2012-11-19
 */
package com.osight.core.dao.impl;

import java.util.List;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.osight.core.dao.UserDAO;
import com.osight.core.pojos.UserData;
import com.osight.framework.hibernate.BaseHibernateDAO;

/**
 * @author chenw
 * @version $Id$
 */
@Repository("userDao")
public class UserDAOImpl extends BaseHibernateDAO implements UserDAO {

	@Override
	public UserData getUserData(String id) {
		return (UserData) hibernateUtil.getObject(id, UserData.class);
	}

	@Override
	public UserData saveOrUpdate(UserData user) {
		if (user.getId() == 0) {
			hibernateUtil.save(user);
		} else {
			hibernateUtil.update(user);
		}
		return user;
	}

	@Override
	public UserData getUserByEmail(String email) {
		List<UserData> list = hibernateUtil.find("select p from UserData p where p.email=?", new Object[] { email },
				new Type[] { StringType.INSTANCE });
		return list.isEmpty() ? null : list.get(0);
	}

    @Override
    public UserData getUserByUserName(String userName) {
        List<UserData> list = hibernateUtil.find("select p from UserData p where p.userName=?", new Object[] { userName },
                new Type[] { StringType.INSTANCE });
        return list.isEmpty() ? null : list.get(0);
    }

}
