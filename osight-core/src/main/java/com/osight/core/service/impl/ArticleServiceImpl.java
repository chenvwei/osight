package com.osight.core.service.impl;

import java.util.List;

import com.osight.core.dao.ArticleDAO;
import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;
import com.osight.core.service.ArticleService;
import com.osight.framework.page.Page;
import com.osight.framework.service.BaseDbService;

public class ArticleServiceImpl extends BaseDbService implements ArticleService {
    private ArticleDAO articleDao;

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
    public ArticleData getArticleById(long id) {
        return articleDao.getArticleById(id);
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

}
