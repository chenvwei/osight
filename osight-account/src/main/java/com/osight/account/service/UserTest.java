/*
 * Created on 2012-11-19
 */
package com.osight.account.service;

import com.osight.account.pojos.UserData;

/**
 * @author chenw <a href="mailto:chenw@chsi.com.cn">chen wei</a>
 * @version $Id$
 */
public class UserTest {
    public static void main(String[] args) {
        UserService userService = UserServiceFactory.getAcademeServiceLocal();
        UserData u = userService.getUserData("2");
        System.out.println(u.getName()+":"+u.getEmail());
    }
}
