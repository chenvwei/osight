/*
 * Created on 2012-12-25
 */
package com.osight.history.service;

import com.osight.history.pojo.TestPojo;

/**
 * @author chenw
 * @version $Id$
 */
public interface TestPojoService {
    TestPojo newPojo(String name, String password, int sex, String email);

    TestPojo updateEmail(long id, String email);
}
