/*
 * Created on 2013-1-4
 */
package com.osight.core.dao.impl.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.osight.core.dao.impl.ArticleDAOImpl;
import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleCommentData;
import com.osight.core.pojos.ArticleData;
import com.osight.framework.page.Page;
import com.osight.memcached.CacheClient;

/**
 * @author chenw
 * @version $Id$
 */

@Repository("articleDao")
public class ArticleDAOCacheImpl extends ArticleDAOImpl {
    private String[] cacheArticleGroup = {"articleGroup"};
    private String[] cacheCommentGroup = {"commentGroup"};
    private String[] cacheCategoryGroup = {"categoryGroup"};
    @Autowired
    @Qualifier("cacheClient")
    private CacheClient cacheClient;

    @Override
    public Page<ArticleData> getArticles(int start, int count) {
        String key = String.format("article_page_%s_%s", start, count);
        Page<ArticleData> page = cacheClient.getGroupCache(key, cacheArticleGroup);
        if (page == null) {
            page = super.getArticles(start, count);
            if (page != null) {
                cacheClient.setCacheWithTime(key, page);
            }
        } else {
            log.info("get from cache,key={}", key);
        }
        return page;
    }

    @Override
    public ArticleData getArticleById(long id) {
        String key = String.format("article_id_%s", id);
        ArticleData article = cacheClient.get(key);
        if (article == null) {
            article = super.getArticleById(id);
            if (article != null) {
                cacheClient.set(key, article);
            }
        } else {
            log.info("get from cache,key={}", key);
        }
        return article;
    }

    @Override
    public ArticleData saveOrUpate(ArticleData data) {
        boolean insert = (data.getId() == 0);
        ArticleData article = super.saveOrUpate(data);
        if (!insert) {
            cacheClient.delete(String.format("article_id_%s", data.getId()));
        }
        cacheClient.flushGroups(cacheArticleGroup);
        cacheClient.flushGroups(cacheCategoryGroup);
        return article;
    }
    
    @Override
    public void delete(ArticleData data) {
    	super.delete(data);
    	cacheClient.delete(String.format("article_id_%s", data.getId()));
    	cacheClient.flushGroups(cacheArticleGroup);
        cacheClient.flushGroups(cacheCategoryGroup);
    }

    @Override
    public ArticleCategoryData getCategoryById(long id) {
        String key = String.format("article_category_id_%s", id);
        ArticleCategoryData acd = cacheClient.getGroupCache(key, cacheCategoryGroup);
        if (acd == null) {
            acd = super.getCategoryById(id);
            if (acd != null) {
                cacheClient.setCacheWithTime(key, acd);
            }
        }
        return acd;
    }

    @Override
    public Page<ArticleCommentData> getCommentsByArticleId(long articleId, int start, int count) {
        String key = String.format("artilce_comments_artcileId_%s_start_%s_count_%s", articleId, start, count);
        Page<ArticleCommentData> page = cacheClient.getGroupCache(key, cacheCommentGroup);
        if (page == null) {
            page = super.getCommentsByArticleId(articleId, start, count);
            if (page != null) {
                cacheClient.setCacheWithTime(key, page);
            }
        }
        return page;
    }

    @Override
    public ArticleCommentData saveOrUpdate(ArticleCommentData data) {
        boolean update = (data.getId() != 0);
        ArticleCommentData acd = super.saveOrUpdate(data);
        if (update) {
            cacheClient.flushGroups(cacheCommentGroup);
        }
        return acd;
    }
}
