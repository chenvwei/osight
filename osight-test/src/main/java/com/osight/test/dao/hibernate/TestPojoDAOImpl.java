/*
 * Created on 2012-12-31
 */
package com.osight.test.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.osight.framework.dao.hibernate.BaseHibernateDAO;
import com.osight.test.dao.TestPojoDAO;
import com.osight.test.pojo.TestPojoData;

/**
 * @author chenw
 * @version $Id$
 */
@Repository("testDAO")
public class TestPojoDAOImpl extends BaseHibernateDAO<TestPojoData, Long> implements TestPojoDAO {

}
