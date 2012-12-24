/*
 * Created on 2012-11-19
 */
package com.osight.core.service;

import com.osight.core.pojos.UserData;

/**
 * @author chenw 
 * @version $Id$
 */
public interface UserService {
    UserData getUserData(String id);
    UserData createUser(String nickName,String email);
    UserData createUser(String nickName,String email,String userName,String password);
    UserData getUserByEmail(String email);
    UserData getUserByUserName(String userName);
}
