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
}
