/*
 * Created on 2012-12-31
 */
package com.osight.test.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.osight.test.dao.TestPojoDAO;
import com.osight.test.pojo.TestPojoData;

/**
 * @author chenw
 * @version $Id$
 */
@Repository("testDAO")
public class TestPojoDAOImpl implements TestPojoDAO {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public TestPojoData saveOrUpdata(TestPojoData data) {
        if (data.getId() == 0) {
            getSession().save(data);
        } else {
            getSession().update(data);
        }
        return data;
    }

    @Override
    public TestPojoData getPojoById(long id) {
        return (TestPojoData) getSession().get(TestPojoData.class, id);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
