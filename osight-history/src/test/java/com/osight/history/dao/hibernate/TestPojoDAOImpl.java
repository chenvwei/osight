/*
 * Created on 2012-12-25
 */
package com.osight.history.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.osight.framework.hibernate.BaseHibernateDAO;
import com.osight.history.dao.TestPojoDAO;
import com.osight.history.pojo.TestPojo;

/**
 * @author chenw
 * @version $Id$
 */
@Repository("testPojoDAO")
public class TestPojoDAOImpl extends BaseHibernateDAO implements TestPojoDAO {

    @Override
    public TestPojo saveOrUpdate(TestPojo data) {
        if (data.getId() == 0)
            hibernateUtil.save(data);
        else
            hibernateUtil.update(data);
        return data;
    }

    @Override
    public TestPojo getTestPojoById(long id) {
        return (TestPojo) hibernateUtil.getObject(id, TestPojo.class);
    }

    @Override
    public void delete(long id) {
        TestPojo data = getTestPojoById(id);
        hibernateUtil.delete(data);
    }

}
