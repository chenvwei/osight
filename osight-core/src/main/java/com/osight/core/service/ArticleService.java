package com.osight.core.service;

import com.osight.core.pojos.ArticleData;

public interface ArticleService {
	ArticleData newArticle(String nickName,String email,String title,String content);
	ArticleData getArticleById(long id);
}
