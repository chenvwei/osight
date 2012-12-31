/*
 * Created on 2012-12-31
 */
package com.osight.test.dao;

import com.osight.test.pojo.TestPojoData;

/**
 * @author chenw
 * @version $Id$
 */
public interface TestPojoDAO {
    TestPojoData saveOrUpdata(TestPojoData data);

    TestPojoData getPojoById(long id);
}
