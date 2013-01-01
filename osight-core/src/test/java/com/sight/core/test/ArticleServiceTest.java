package com.sight.core.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.osight.core.pojos.ArticleCategoryData;
import com.osight.core.pojos.ArticleCommentData;
import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;
import com.osight.core.service.ArticleService;
import com.osight.core.service.ArticleServiceFactory;
import com.osight.core.service.UserService;
import com.osight.core.service.UserServiceFactory;
import com.osight.framework.page.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-core.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class ArticleServiceTest {
	private Logger	log		= LoggerFactory.getLogger(getClass());
	ArticleService	service	= ArticleServiceFactory.getArticleService();

	@Test
	public void test() {
		log.info("service:" + service);
	}

	@Test
	public void testUpdate() {
		UserService userService = UserServiceFactory.getUserService();
		UserData user = userService.createUser("rodneytt", "rodneytt@sina.com");
		ArticleData data = service.newArticle(user, "中国心", "今天是世界末日");
		ArticleCategoryData ct = service.newCategory("我的类别");
		log.info("0:"+ct.toString());
		log.info("1:"+data.getCategory());
		data.setCategory(new ArticleCategoryData(ct.getId()));
		service.updateArticle(data);
		ArticleData d = service.getArticleById(data.getId());
		log.info("2:"+d.getCategory());
	}

	@Test
	public void testNew() {
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
		Assert.assertEquals(page.getList().size(), 1);
		for (ArticleCommentData cmt : page.getList()) {
			log.info(cmt.toString());
		}
	}
}
