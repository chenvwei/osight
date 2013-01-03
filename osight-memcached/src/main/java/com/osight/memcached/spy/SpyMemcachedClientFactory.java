package com.osight.memcached.spy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.memcached.cfg.DefaultProp;
import com.osight.memcached.cfg.PoolConfig;
import com.osight.memcached.cfg.PoolConfigService;

public class SpyMemcachedClientFactory {
	protected static Logger log = LoggerFactory.getLogger(SpyMemcachedClientFactory.class);

	private static Object synObj = new Object();
	static PoolConfigService cfgService;

	private static Map<String, SpyMemcachedClient> clientMaps = new ConcurrentHashMap<String, SpyMemcachedClient>();

	public static SpyMemcachedClient getSpyMemcachedClient() {
		return getSpyMemcachedClient(DefaultProp.POOL_NAME, DefaultProp.APP_NAME);
	}

	public static SpyMemcachedClient getSpyMemcachedClient(String poolName, String appName, String configClassName) {
		if (null == DefaultProp.CFGSERVICE_CLASS) {
			DefaultProp.CFGSERVICE_CLASS = configClassName;
			try {
				Class.forName(configClassName);
				log.info("缓存池配置读取类：" + DefaultProp.CFGSERVICE_CLASS);
			} catch (ClassNotFoundException e) {
				log.error("Class not found:" + configClassName, e);
			}
		}

		return getSpyMemcachedClient(poolName, appName);
	}

	public SpyMemcachedClient getSpyMemcachedClient2(String poolName, String appName) {
		return getSpyMemcachedClient(poolName, appName);
	}

	public static SpyMemcachedClient getSpyMemcachedClient(String poolName, String appName) {
		if ((null == poolName) || (null == appName)) {
			throw new IllegalArgumentException("参数不能为空");
		}
		String key = poolName + appName;
		SpyMemcachedClient client = (SpyMemcachedClient) clientMaps.get(key);
		if (null == client) {
			synchronized (synObj) {
				client = (SpyMemcachedClient) clientMaps.get(key);
				if (null == client) {
					if (null == cfgService) {
						try {
							Class<?> c = Class.forName(DefaultProp.CFGSERVICE_CLASS);
							cfgService = (PoolConfigService) c.newInstance();
						} catch (ClassNotFoundException e) {
							log.error("类不存在:" + DefaultProp.CFGSERVICE_CLASS, e);
						} catch (InstantiationException e) {
							log.error("创建类实例错误:" + DefaultProp.CFGSERVICE_CLASS, e);
						} catch (IllegalAccessException e) {
							log.error("创建类实例错误:" + DefaultProp.CFGSERVICE_CLASS, e);
						}
					}

					PoolConfig poolConfig = null;
					try {
						poolConfig = cfgService.getPoolConfigByName(poolName);
					} catch (Throwable e) {
						log.error(String.format("读取缓存池[%s]配置错误", new Object[] { poolName }), e);
						return null;
					}
					if (null == poolConfig) {
						log.error(String.format("pool[%s] 不存在", new Object[] { poolName }));
						return null;
					}

					client = new SpyMemcachedClient(appName, poolConfig);
					clientMaps.put(key, client);
					if (log.isDebugEnabled()) {
						log.debug(String.format("pool[%s] 初始化完毕", new Object[] { poolName }));
					}
				}
			}
		} else if (log.isDebugEnabled()) {
			log.debug(String.format("从Map中获取pool[%s]完毕", new Object[] { poolName }));
		}

		return client;
	}

	public static PoolConfigService getCfgService() {
		return cfgService;
	}

	public static void setCfgService(PoolConfigService cfgService) {
		SpyMemcachedClientFactory.cfgService = cfgService;
	}

}
