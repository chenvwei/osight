package com.osight.memcached;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osight.memcached.spy.SpyMemcachedClientFactory;

public class MemcachedTest {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {

	}

	@Test
	public void testClient() {
		CacheClient client = SpyMemcachedClientFactory.getSpyMemcachedClient();
		client.set("name", "rodneytt");
		String name = client.get("name");
		log.info(name);
		Assert.assertEquals(name, "rodneytt");
	}
}
