package com.osight.core.service;

import java.util.List;

import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleCommentData;
import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;
import com.osight.framework.page.Page;

public interface ArticleService {
	ArticleData newArticle(UserData user, String title, String content);

	ArticleData newArticle(ArticleData article);

	ArticleData updateArticle(ArticleData article);

	ArticleData updateArticle(long id, String title, String content);

	ArticleData getArticleById(long id);

	List<ArticleData> getAllArticles();

	Page<ArticleData> getArticles(int start, int count,boolean secretVisable);

	void deleteArticleById(long id);

	void increasePV(long id);

	List<ArticleCategoryData> getCategorys();

	ArticleCategoryData newCategory(String name);

	ArticleCategoryData getCategoryById(long id);

	boolean categoryExists(String name);

	ArticleCommentData newComment(ArticleCommentData data);

	Page<ArticleCommentData> getCommentsByArticleId(long articleId, int start, int count);
}
