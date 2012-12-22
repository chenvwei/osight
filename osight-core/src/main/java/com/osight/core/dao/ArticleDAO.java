package com.osight.core.dao;

import java.util.List;

import com.osight.core.pojos.ArticleData;

public interface ArticleDAO {
	ArticleData saveOrUpate(ArticleData data);

	ArticleData getArticleById(long id);

	List<ArticleData> getAllArticles();
}
