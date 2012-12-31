/*
 * Created on 2012-12-31
 */
package com.osight.test.service;

import com.osight.test.pojo.TestPojoData;

/**
 * @author chenw
 * @version $Id$
 */
public interface TestService {
    TestPojoData newPojo(String name, String sex, String email);

    TestPojoData gePojoById(long id);
}
