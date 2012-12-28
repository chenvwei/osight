package com.osight.core.dao.impl;

import java.util.List;

import com.osight.core.dao.ArticleDAO;
import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleData;
import com.osight.framework.hibernate.BaseHibernateDAO;
import com.osight.framework.page.Page;
import com.osight.framework.page.PageUtil;

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
	public List<ArticleCategoryData> getCategorys() {
		return hibernateUtil.find("select p from ArticleCategoryData p");
	}

	@Override
	public void delete(ArticleData data) {
		hibernateUtil.delete(data);
	}

	@Override
	public ArticleData getArticleById(long id) {
		return (ArticleData) hibernateUtil.getObject(id, ArticleData.class);
	}

	@Override
	public List<ArticleData> getAllArticles() {
		return hibernateUtil.find("select p from ArticleData p order by p.createdOn desc");
	}

	@Override
	public Page<ArticleData> getArticles(int start, int count) {
		List<ArticleData> list = hibernateUtil.find("select p from ArticleData p order by p.createdOn desc", null, null,
				start, count);
		long ct = hibernateUtil.getCount("select p from ArticleData p", null, null);
		Page<ArticleData> page = PageUtil.getPage(list.iterator(), start, count, ct);
		return page;
	}

	@Override
	public ArticleCategoryData saveOrUpdate(ArticleCategoryData data) {
		if (data.getId() == 0) {
			hibernateUtil.save(data);
		} else {
			hibernateUtil.update(data);
		}
		return data;
	}

	@Override
	public ArticleCategoryData getArticleCategoryById(long id) {
		return (ArticleCategoryData) hibernateUtil.getObject(id, ArticleCategoryData.class);
	}

}
