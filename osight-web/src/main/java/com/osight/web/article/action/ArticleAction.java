package com.osight.web.article.action;

import com.osight.core.pojos.ArticleData;
import com.osight.core.service.ArticleService;
import com.osight.framework.struts2.BasicSupportAction;

public class ArticleAction extends BasicSupportAction {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 1L;

	private ArticleService		articleService;
	private ArticleData			article;

	public String list() {
		return "list";
	}

	public String add() {
		return "add";
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
	
}
