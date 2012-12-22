package com.osight.core.service.impl;

import com.osight.core.dao.ArticleDAO;
import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;
import com.osight.core.service.ArticleService;
import com.osight.core.service.UserService;
import com.osight.framework.service.BaseDbService;

public class ArticleServiceImpl extends BaseDbService implements ArticleService {
	private ArticleDAO	articleDao;
	private UserService	userService;

	@Override
	public ArticleData newArticle(String nickName, String email, String title, String content) {
		UserData user = userService.createUser(nickName, email);
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

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
