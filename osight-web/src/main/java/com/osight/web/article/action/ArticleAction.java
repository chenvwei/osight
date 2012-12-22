package com.osight.web.article.action;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.core.pojos.ArticleData;
import com.osight.core.service.ArticleService;
import com.osight.framework.struts2.BasicSupportAction;

public class ArticleAction extends BasicSupportAction {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 1L;
	private Logger				log					= LoggerFactory.getLogger(getClass());

	private ArticleService		articleService;
	private ArticleData			article;
	private List<ArticleData>	articleList;
	private long				id;

	public String view() {
		article = articleService.getArticleById(id);
		return "view";
	}

	public String list() {
		articleList = articleService.getAllArticles();
		return "list";
	}

	public String add() {
		return "add";
	}

	public String save() {
		article = articleService.newArticle(article.getUser().getNickName(), article.getUser().getEmail(),
				article.getTitle(), article.getContent());
		log.info(ToStringBuilder.reflectionToString(article, ToStringStyle.MULTI_LINE_STYLE));
		return list();
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

	public List<ArticleData> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleData> articleList) {
		this.articleList = articleList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
