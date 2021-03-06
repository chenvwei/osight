package com.osight.core.dao.impl;

import java.util.List;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import com.osight.core.dao.ArticleDAO;
import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleCommentData;
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
		Page<ArticleCommentData> cpage = getCommentsByArticleId(data.getId(), 0, Integer.MAX_VALUE);
		if (cpage != null && !cpage.getList().isEmpty()) {
			for (ArticleCommentData c : cpage.getList()) {
				hibernateUtil.delete(c);
			}
		}
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
	public ArticleCategoryData getCategoryById(long id) {
		return (ArticleCategoryData) hibernateUtil.getObject(id, ArticleCategoryData.class);
	}

	@Override
	public ArticleCategoryData getCategoryByName(String name) {
		List<ArticleCategoryData> list = hibernateUtil.find("select p from ArticleCategoryData p where p.name=?",
				new Object[] { name }, new Type[] { StringType.INSTANCE });
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public ArticleCommentData saveOrUpdate(ArticleCommentData data) {
		if (data.getId() == 0) {
			hibernateUtil.save(data);
		} else {
			hibernateUtil.update(data);
		}
		return data;
	}

	@Override
	public Page<ArticleCommentData> getCommentsByArticleId(long articleId, int start, int count) {
		List<ArticleCommentData> list = hibernateUtil.find("select p from ArticleCommentData p where p.articleId=?",
				new Object[] { articleId }, new Type[] { LongType.INSTANCE }, start, count);
		long ct = hibernateUtil.getCount("select p from ArticleCommentData p where p.articleId=?",
				new Object[] { articleId }, new Type[] { LongType.INSTANCE });
		Page<ArticleCommentData> page = PageUtil.getPage(list.iterator(), start, count, ct);
		return page;
	}
}
