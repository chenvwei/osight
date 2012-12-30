package com.sight.core.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleCommentData;
import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;
import com.osight.core.service.ArticleService;
import com.osight.core.service.ArticleServiceFactory;
import com.osight.core.service.UserService;
import com.osight.core.service.UserServiceFactory;
import com.osight.framework.page.Page;

public class ArticleServiceTest {
	private Logger	log	= LoggerFactory.getLogger(getClass());

	@Test
	public void test() {

	}

	@Test
	public void testNew() {
		ArticleService service = ArticleServiceFactory.getArticleService();
		UserService userService = UserServiceFactory.getUserService();
		UserData user = userService.createUser("rodneytt", "rodneytt@sina.com");
		ArticleData data = service.newArticle(user, "中国心", "今天是世界末日");
		ArticleCategoryData ct = new ArticleCategoryData();
		ct.setId(service.newCategory("我的类别").getId());
		data.setCategory(ct);
		data = service.newArticle(data);
		ArticleCommentData c = new ArticleCommentData();
		c.setUserName("rodneytt");
		c.setEmail("rodneytt@sina.com");
		c.setContent("bbb");
		c.setArticleId(data.getId());
		service.newComment(c);

		Page<ArticleCommentData> page = service.getCommentsByArticleId(data.getId(), 0, 10);
		System.out.println(page);
	}

}
