package com.osight.core.dao.impl;

import com.osight.core.dao.ArticleDAO;
import com.osight.core.pojos.ArticleData;
import com.osight.framework.hibernate.BaseHibernateDAO;

public class ArticleDAOImpl extends BaseHibernateDAO implements ArticleDAO {

	@Override
	public ArticleData saveOrUpate(ArticleData data) {
		if (data.getId() == 0) {
			hibernateUtil.save(data);
		} else {
			hibernateUtil.update(data);
		}
		return data;
	}

	@Override
	public ArticleData getArticleById(long id) {
		return (ArticleData)hibernateUtil.getObject(id, ArticleData.class);
	}

}
