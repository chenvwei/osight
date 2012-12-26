package com.sight.core.test;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.core.pojos.ArticleData;
import com.osight.core.pojos.UserData;
import com.osight.core.service.ArticleService;
import com.osight.core.service.ArticleServiceFactory;
import com.osight.core.service.UserService;
import com.osight.core.service.UserServiceFactory;

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
		log.info(ToStringBuilder.reflectionToString(data, ToStringStyle.MULTI_LINE_STYLE));
		ArticleData d1 = service.getArticleById(data.getId());
		log.info(ToStringBuilder.reflectionToString(d1, ToStringStyle.MULTI_LINE_STYLE));
		log.info(ToStringBuilder.reflectionToString(d1.getUser(), ToStringStyle.MULTI_LINE_STYLE));
	}
}
