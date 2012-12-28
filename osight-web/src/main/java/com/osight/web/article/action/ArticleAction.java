package com.osight.web.article.action;

import org.apache.struts2.ServletActionContext;

import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;
import com.osight.core.service.ArticleService;
import com.osight.core.util.WebAppUtil;
import com.osight.framework.page.Page;
import com.osight.framework.struts2.BasicSupportAction;

public class ArticleAction extends BasicSupportAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static final int pageCount = 5;
    private ArticleService articleService;
    private ArticleData article;
    private long id;
    private int pageNum;
    private Page<ArticleData> page;

    public String view() {
        article = articleService.getArticleById(id);
        articleService.increasePV(id);
        return "view";
    }

    public String edit() {
        article = articleService.getArticleById(id);
        return "edit";
    }

    public String delete() {
        articleService.deleteArticleById(id);
        return "list";
    }

    public String list() {
        if (pageNum < 1) {
            pageNum = 1;
        }
        page = articleService.getArticles((pageNum - 1) * pageCount, pageCount);
        return "list";
    }

    public String add() {
        return "add";
    }

    public String save() {
        if (article.getId() == 0) {
            UserData user = WebAppUtil.getLoginUser(ServletActionContext.getRequest());
            article = articleService.newArticle(user, article.getTitle(), article.getContent());
        } else {
            article = articleService.updateArticle(article.getId(), article.getTitle(), article.getContent());
        }
        return "view";
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public ArticleData getArticle() {
        return article;
    }

    public void setArticle(ArticleData article) {
        this.article = article;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Page<ArticleData> getPage() {
        return page;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
