/*
 * Created on 2012-12-28
 */
package com.osight.web.article.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleData;
import com.osight.core.service.ArticleService;
import com.osight.framework.page.Page;
import com.osight.framework.page.PageUtil;
import com.osight.framework.struts2.BasicSupportAction;

/**
 * @author chenw
 * @version $Id$
 */
public class CategoryAction extends BasicSupportAction {
	private static final long	serialVersionUID	= 1L;
	private ArticleService		articleService;
	private int					id;
	private String				name;
	private Page<ArticleData>	page;

	public String newCategory() {
		ArticleCategoryData ct = articleService.newCategory(name);
		JSONObject json = new JSONObject();
		json.element("id", ct.getId()).element("name", ct.getName());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public String listArticle() {
		ArticleCategoryData category = articleService.getArticleCategoryById(id);
		if (category != null && category.getArticles() != null) {
			page = PageUtil.getPage(category.getArticles().iterator(), 0, category.getArticles().size(), category
					.getArticles().size());
		}
		return "list";
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Page<ArticleData> getPage() {
		return page;
	}

	public void setId(int id) {
		this.id = id;
	}

}
