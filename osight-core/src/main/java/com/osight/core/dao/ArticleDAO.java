package com.osight.core.dao;

import java.util.List;

import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleCommentData;
import com.osight.core.pojos.ArticleData;
import com.osight.framework.page.Page;

public interface ArticleDAO {
	ArticleData saveOrUpate(ArticleData data);

	ArticleData getArticleById(long id);

	List<ArticleData> getAllArticles();

	void delete(ArticleData data);

	Page<ArticleData> getArticles(int start, int count,boolean visible);

	List<ArticleCategoryData> getCategorys();

	ArticleCategoryData saveOrUpdate(ArticleCategoryData data);

	ArticleCategoryData getCategoryById(long id);

	ArticleCategoryData getCategoryByName(String name);

	ArticleCommentData saveOrUpdate(ArticleCommentData data);

	Page<ArticleCommentData> getCommentsByArticleId(long articleId, int start, int count);

}
