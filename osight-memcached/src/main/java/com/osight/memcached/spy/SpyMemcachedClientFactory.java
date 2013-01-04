package com.osight.memcached.spy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.memcached.cfg.DefaultProp;
import com.osight.memcached.cfg.PoolConfig;

public class SpyMemcachedClientFactory {
	protected static Logger log = LoggerFactory.getLogger(SpyMemcachedClientFactory.class);

	private static Object synObj = new Object();

	private static Map<String, SpyMemcachedClient> clientMaps = new ConcurrentHashMap<String, SpyMemcachedClient>();

	public static SpyMemcachedClient getSpyMemcachedClient() {
		return getSpyMemcachedClient(DefaultProp.POOL_NAME, DefaultProp.APP_NAME);
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
					PoolConfig poolConfig = initConfig(poolName);
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
	private static PoolConfig initConfig(String poolName){
		if(DefaultProp.SERVERS!=null){
			PoolConfig cfg = new PoolConfig();
			cfg.setAliveCheck(true);
			cfg.setCabinet(true);
			cfg.setFailOver(true);
			cfg.setInitConn(5);
			cfg.setMaintSleep(10);
			cfg.setMinConn(5);
			cfg.setMaxConn(1000);
			cfg.setNagle(false);
			cfg.setPoolName(poolName);
			cfg.setSocketTimeout(2048);
			if(DefaultProp.SERVERS.indexOf(";")!=-1){
				cfg.setServers(new String[] { DefaultProp.SERVERS});
			}else{
				cfg.setServers(DefaultProp.SERVERS.split(";"));
			}
			return cfg;
		}
		return null;
	}

}
