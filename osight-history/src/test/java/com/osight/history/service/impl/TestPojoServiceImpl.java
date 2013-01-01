/*
 * Created on 2012-12-25
 */
package com.osight.history.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osight.history.dao.TestPojoDAO;
import com.osight.history.pojo.TestPojo;
import com.osight.history.service.TestPojoService;

/**
 * @author chenw
 * @version $Id$
 */
@Service("testPojoService")
@Transactional
public class TestPojoServiceImpl implements TestPojoService {
	@Autowired
	@Qualifier("testPojoDAO")
	private TestPojoDAO	dao;

	@Override
	public TestPojo newPojo(String name, String password, int sex, String email) {
		TestPojo data = new TestPojo();
		data.setName(name);
		data.setPassword(password);
		data.setSex(sex);
		data.setEmail(email);
		return dao.saveOrUpdate(data);
	}

	@Override
	public TestPojo updateEmail(long id, String email) {
		TestPojo data = dao.getTestPojoById(id);
		data.setEmail(email);
		return dao.saveOrUpdate(data);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}

}
