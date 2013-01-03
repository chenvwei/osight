package com.osight.memcached.cfg;

public class PoolConfig {
	String		poolName;
	boolean		failOver;
	int			initConn;
	int			minConn;
	int			maxConn;
	int			maintSleep;
	boolean		nagle;
	int			socketTimeout;
	boolean		aliveCheck;
	boolean		cabinet;
	String[]	servers;
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public boolean isFailOver() {
		return failOver;
	}
	public void setFailOver(boolean failOver) {
		this.failOver = failOver;
	}
	public int getInitConn() {
		return initConn;
	}
	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}
	public int getMinConn() {
		return minConn;
	}
	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}
	public int getMaxConn() {
		return maxConn;
	}
	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}
	public int getMaintSleep() {
		return maintSleep;
	}
	public void setMaintSleep(int maintSleep) {
		this.maintSleep = maintSleep;
	}
	public boolean isNagle() {
		return nagle;
	}
	public void setNagle(boolean nagle) {
		this.nagle = nagle;
	}
	public int getSocketTimeout() {
		return socketTimeout;
	}
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
	public boolean isAliveCheck() {
		return aliveCheck;
	}
	public void setAliveCheck(boolean aliveCheck) {
		this.aliveCheck = aliveCheck;
	}
	public boolean isCabinet() {
		return cabinet;
	}
	public void setCabinet(boolean cabinet) {
		this.cabinet = cabinet;
	}
	public String[] getServers() {
		return servers;
	}
	public void setServers(String[] servers) {
		this.servers = servers;
	}
	
	
}
