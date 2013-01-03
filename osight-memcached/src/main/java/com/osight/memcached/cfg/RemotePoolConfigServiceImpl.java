package com.osight.memcached.cfg;

public class RemotePoolConfigServiceImpl implements PoolConfigService {

	@Override
	public PoolConfig getPoolConfigByName(String poolName) {
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
		cfg.setServers(new String[] { "42.121.30.206:11111" });
		return cfg;
	}

}
