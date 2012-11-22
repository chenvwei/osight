/*
 * Created on 2012-11-19
 */
package com.osight.core.dao;

import com.osight.core.pojos.UserData;

/**
 * @author chenw 
 * @version $Id$
 */
public interface UserDAO {
    UserData getUserData(String id);
}
