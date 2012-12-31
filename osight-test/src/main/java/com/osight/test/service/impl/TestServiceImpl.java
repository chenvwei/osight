/*
 * Created on 2012-12-31
 */
package com.osight.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.osight.test.dao.TestPojoDAO;
import com.osight.test.pojo.TestPojoData;
import com.osight.test.service.TestService;

/**
 * @author chenw
 * @version $Id$
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    private TestPojoDAO testDao;

    @Autowired
    @Qualifier("testDAO")
    public void setTestDao(TestPojoDAO testDao) {
        this.testDao = testDao;
    }

    @Override
    public TestPojoData newPojo(String name, String sex, String email) {
        TestPojoData pojo = new TestPojoData();
        pojo.setName(name);
        pojo.setSex(sex);
        pojo.setEmail(email);
        return testDao.saveOrUpdata(pojo);
    }

    @Override
    public TestPojoData gePojoById(long id) {
        return testDao.getPojoById(id);
    }

}
