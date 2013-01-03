package com.osight.memcached;

import java.util.Date;
import java.util.Map;

public interface CacheClient {
	public boolean add(String key, Object value);

	public boolean add(String key, Object value, Date date);

	public long addOrDecr(String key, long value);

	public long addOrIncr(String key, long value);

	public long decr(String key);

	public long decr(String key, long value);

	public boolean delete(String key);

	public boolean flushAll();

	public boolean flushAll(String[] keys);

	public <T> T get(String key);

	public <T> T get(String key, Class<T> cls);
	
	public <T> Map<String, T> getMulti(String[] groups);

	public <T> T getGroupCache(String key, String[] groups);

	public void setCacheWithTime(String key, Object value);

	public void flushGroups(String[] groups);

	public long getCounter(String key);

	public long incr(String key);

	public long incr(String key, long value);

	public boolean keyExists(String key);

	public boolean replace(String key, Object value);

	public boolean replace(String key, Object value, Date date);

	public boolean set(String key, Object value);

	public boolean set(String key, Object value, Date date);

	public void setCompressEnable(boolean enable);

	public void setCompressThreshold(long num);

	public void setDefaultEncoding(String key);

	public boolean storeCounter(String key, long value);

	public boolean storeCounter(String key, Long value);

	public String sanitizeKey(String key);

	public void shutdown();
}
