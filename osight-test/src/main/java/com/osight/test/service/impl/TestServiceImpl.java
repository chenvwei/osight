/*
 * Created on 2012-12-31
 */
package com.osight.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.osight.framework.dao.IBaseDAO;
import com.osight.framework.service.impl.BaseService;
import com.osight.test.pojo.TestPojoData;
import com.osight.test.service.TestService;

/**
 * @author chenw
 * @version $Id$
 */
@Service("testService")
public class TestServiceImpl extends BaseService<TestPojoData, Long> implements TestService {

    @Autowired
    @Qualifier("testDAO")
    @Override
    public void setBaseDao(IBaseDAO<TestPojoData, Long> baseDao) {
        this.baseDao = baseDao;
    }

}
