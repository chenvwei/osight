package com.osight.core.service;

import com.osight.framework.service.spring.SpringBeanLocator;

public class ArticleServiceFactory {
	public static ArticleService getArticleService() {
		return SpringBeanLocator.getInstance("serviceImpl.xml").getBean("articleService");
	}
}
