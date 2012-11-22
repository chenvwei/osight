/*
 * Created on 2012-11-19
 */
package com.osight.core.dao.impl;

import com.osight.core.dao.UserDAO;
import com.osight.core.pojos.UserData;
import com.osight.framework.hibernate.BaseHibernateDAO;

/**
 * @author chenw 
 * @version $Id$
 */
public class UserDAOImpl extends BaseHibernateDAO implements UserDAO {

    @Override
    public UserData getUserData(String id) {
        return (UserData) hibernateUtil.getObject(id, UserData.class);
    }

}
