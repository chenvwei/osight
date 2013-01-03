package com.osight.memcached.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.memcached.CacheClient;
import com.osight.memcached.helper.TimedObject;

public abstract class AbstractCacheClient implements CacheClient {

	protected Logger	log	= LoggerFactory.getLogger(getClass());

	@Override
	public boolean add(String key, Object value) {
		return add(key, value, null);
	}

	@Override
	public long decr(String key) {
		return decr(key, 1);
	}

	@Override
	public long getCounter(String key) {
		Long l = (Long) get(key);
		if (l == null) {
			return 0;
		} else {
			return l.longValue();
		}
	}

	@Override
	public long incr(String key) {
		return incr(key, 1);
	}

	@Override
	public boolean keyExists(String key) {
		return get(key) != null;
	}

	@Override
	public boolean replace(String key, Object value) {
		return replace(key, value, null);
	}

	@Override
	public <T> T get(String key, Class<T> cls) {
		Object o = get(key);
		try {
			return cls.cast(o);
		} catch (ClassCastException e) {
			this.log.error(String.format("Class cast error, key[%s]", new Object[] { key }), e);
		}
		return null;
	}

	@Override
	public boolean set(String key, Object value) {
		return set(key, value, null);
	}

	@Override
	public boolean storeCounter(String key, long value) {
		return set(key, value);
	}

	@Override
	public boolean storeCounter(String key, Long value) {
		return set(key, value);
	}

	@Override
	public void flushGroups(String[] groups) {
		if (groups == null) {
			return;
		}
		for (String key : groups) {
			set(key, System.currentTimeMillis());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getGroupCache(String key, String[] groups) {
		TimedObject<T> obj = null;
		try {
			obj = (TimedObject<T>) get(key);
		} catch (Exception e) {
			log.error("Cann't read from cache,key={}", key, e);
		}
		boolean fresh = isCacheFresh(groups, obj);

		if (fresh) {
			return obj.getObject();
		}

		return null;
	}

	private boolean isCacheFresh(String[] groups, TimedObject<? extends Object> cachedObj) {
	    if (null == cachedObj)
	      return false;
	    if (null == groups) {
	      return false;
	    }
	    Map<String, ?> expireTimes = getMulti(groups);

	    boolean fresh = true;

	    for (String key : groups) {
	      Long expireTime = (Long)expireTimes.get(sanitizeKey(key));
	      if (null == expireTime)
	      {
	        fresh = false;
	        set(key, Long.valueOf(System.currentTimeMillis()));
	      }
	      else if (expireTime.longValue() > cachedObj.getCacheTime()) {
	        fresh = false;
	      }
	    }

	    return fresh;
	  }
	
	@Override
	public void setCacheWithTime(String key, Object value) {
		TimedObject<Object> obj = new TimedObject<Object>();
		obj.setObject(value);
		set(key, obj);
	}

}
