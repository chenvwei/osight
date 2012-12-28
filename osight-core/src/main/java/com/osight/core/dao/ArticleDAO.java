package com.osight.core.dao;

import java.util.List;

import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleData;
import com.osight.framework.page.Page;

public interface ArticleDAO {
    ArticleData saveOrUpate(ArticleData data);

    ArticleData getArticleById(long id);

    List<ArticleData> getAllArticles();
    
    void delete(ArticleData data);

    Page<ArticleData> getArticles(int start, int count);
    
    List<ArticleCategoryData> getCategorys();
    
    ArticleCategoryData saveOrUpdate(ArticleCategoryData data);
    
    ArticleCategoryData getArticleCategoryById(long id);
}
