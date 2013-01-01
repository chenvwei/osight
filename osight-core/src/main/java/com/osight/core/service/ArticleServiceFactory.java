package com.osight.core.service;

import com.osight.core.Constants;
import com.osight.framework.service.spring.SpringBeanLocator;

public class ArticleServiceFactory {

	public static ArticleService getArticleService() {
		return SpringBeanLocator.getInstance(Constants.SPRING_CONFIG_FILE).getBean("articleService");
	}
}
