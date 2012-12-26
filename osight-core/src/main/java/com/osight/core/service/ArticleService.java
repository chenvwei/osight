package com.osight.core.service;

import java.util.List;

import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;

public interface ArticleService {
	ArticleData newArticle(UserData user, String title, String content);

	ArticleData updateArticle(long id, String title, String content);

	ArticleData getArticleById(long id);

	List<ArticleData> getAllArticles();

	void increasePV(long id);
}
