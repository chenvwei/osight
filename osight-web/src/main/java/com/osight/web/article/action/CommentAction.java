package com.osight.web.article.action;


import com.osight.core.pojos.ArticleCommentData;
import com.osight.core.service.ArticleService;
import com.osight.framework.page.Page;
import com.osight.framework.struts2.BasicSupportAction;

public class CommentAction extends BasicSupportAction {
	private static final long			serialVersionUID	= 1L;
	private static final int			PAGE_SIZE			= 20;
	private long						articleId;
	private ArticleCommentData			comment;
	private Page<ArticleCommentData>	page;
	private int							start;

	private ArticleService				articleService;

	public String add() {
		articleService.newComment(comment);
		return "view";
	}

	public String list() {
		page = articleService.getCommentsByArticleId(articleId, start, PAGE_SIZE);
		return "list";
	}

	public ArticleCommentData getComment() {
		return comment;
	}

	public Page<ArticleCommentData> getPage() {
		return page;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setComment(ArticleCommentData comment) {
		this.comment = comment;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	
}
