package com.osight.memcached.cfg;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultProp {
	private static Logger log = LoggerFactory.getLogger(DefaultProp.class);

	public static String POOL_NAME = "";
	public static String APP_NAME = "";
	public static String CFGSERVICE_CLASS = null;

	private static final String CONFIG_FILE = "memcachedclient.properties";

	private static void init() {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE));
			POOL_NAME = prop.getProperty("POOL_NAME");
			APP_NAME = prop.getProperty("APP_NAME");
			if (null == CFGSERVICE_CLASS) {
				CFGSERVICE_CLASS = prop.getProperty("CFGSERVICE_CLASS");
				Class.forName(CFGSERVICE_CLASS);
			}
		} catch (Exception e) {
			log.error("读取memcached配置文件错误:" + e.getMessage());
		}
	}

	static {
		init();
	}
}
