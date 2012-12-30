package com.osight.core.service.impl;

import java.util.List;

import com.osight.core.dao.ArticleDAO;
import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleCommentData;
import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;
import com.osight.core.service.ArticleService;
import com.osight.framework.page.Page;
import com.osight.framework.service.BaseDbService;

public class ArticleServiceImpl extends BaseDbService implements ArticleService {
	private ArticleDAO	articleDao;

	@Override
	public ArticleData newArticle(UserData user, String title, String content) {
		ArticleData article = new ArticleData();
		article.setUser(user);
		article.setTitle(title);
		article.setContent(content);
		article.setPv(0);
		return articleDao.saveOrUpate(article);
	}

	@Override
	public ArticleData newArticle(ArticleData article) {
		return articleDao.saveOrUpate(article);
	}

	@Override
	public ArticleData updateArticle(ArticleData article) {
		ArticleData data = getArticleById(article.getId());
		if (data != null) {
			data.setTitle(article.getTitle());
			if (article.getCategory() != null && article.getCategory().getId() != 0) {
				data.setCategory(new ArticleCategoryData(article.getCategory().getId()));
			}
			data.setContent(article.getContent());
			return articleDao.saveOrUpate(data);
		}
		return null;
	}

	@Override
	public ArticleData getArticleById(long id) {
		return articleDao.getArticleById(id);
	}

	@Override
	public List<ArticleCategoryData> getCategorys() {
		return articleDao.getCategorys();
	}

	@Override
	protected void doCreate() {
		articleDao = (ArticleDAO) getDAO("articleDAO", ArticleDAO.class);
	}

	@Override
	protected void doRemove() {
	}

	@Override
	public List<ArticleData> getAllArticles() {
		return articleDao.getAllArticles();
	}

	@Override
	public void increasePV(long id) {
		ArticleData d = getArticleById(id);
		if (d != null) {
			d.setPv(d.getPv() + 1);
			articleDao.saveOrUpate(d);
		}
	}

	@Override
	public ArticleData updateArticle(long id, String title, String content) {
		ArticleData data = getArticleById(id);
		if (data != null) {
			data.setTitle(title);
			data.setContent(content);
			articleDao.saveOrUpate(data);
		}
		return data;
	}

	@Override
	public Page<ArticleData> getArticles(int start, int count) {
		return articleDao.getArticles(start, count);
	}

	@Override
	public void deleteArticleById(long id) {
		ArticleData art = getArticleById(id);
		if (art != null) {
			articleDao.delete(art);
		}
	}

	@Override
	public ArticleCategoryData newCategory(String name) {
		ArticleCategoryData data = new ArticleCategoryData(name);
		return articleDao.saveOrUpdate(data);
	}

	@Override
	public ArticleCategoryData getCategoryById(long id) {
		return articleDao.getCategoryById(id);
	}

	@Override
	public boolean categoryExists(String name) {
		ArticleCategoryData c = articleDao.getCategoryByName(name);
		return c == null ? false : true;
	}

	@Override
	public ArticleCommentData newComment(ArticleCommentData data) {
		return articleDao.saveOrUpdate(data);
	}

	@Override
	public Page<ArticleCommentData> getCommentsByArticleId(long articleId, int start, int count) {
		return articleDao.getCommentsByArticleId(articleId, start, count);
	}
}
