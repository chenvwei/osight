package com.osight.memcached.helper;

import java.io.Serializable;

public class TimedObject<T> implements Serializable {

	private static final long	serialVersionUID	= 1L;

	protected long				cacheTime;
	protected T					object;

	public TimedObject() {
		this.cacheTime = System.currentTimeMillis();
	}
	
	public long getCacheTime() {
		return cacheTime;
	}

	public void setCacheTime(long cacheTime) {
		this.cacheTime = cacheTime;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public boolean isExpired(long secs) {
		return System.currentTimeMillis() - this.cacheTime > secs * 1000L;
	}
}
