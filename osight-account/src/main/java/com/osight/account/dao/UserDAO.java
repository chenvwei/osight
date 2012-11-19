/*
 * Created on 2012-11-19
 */
package com.osight.account.dao;

import com.osight.account.pojos.UserData;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public interface UserDAO {
    UserData getUserData(String id);
}
