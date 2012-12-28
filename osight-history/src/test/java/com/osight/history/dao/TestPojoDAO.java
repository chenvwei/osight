/*
 * Created on 2012-12-25
 */
package com.osight.history.dao;

import com.osight.history.pojo.TestPojo;

/**
 * @author chenw
 * @version $Id$
 */
public interface TestPojoDAO {
    TestPojo saveOrUpdate(TestPojo data);

    TestPojo getTestPojoById(long id);
    
    void delete(long id);
}
