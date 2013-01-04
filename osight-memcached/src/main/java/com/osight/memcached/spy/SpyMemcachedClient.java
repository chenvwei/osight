package com.osight.memcached.spy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

import com.osight.memcached.cfg.PoolConfig;
import com.osight.memcached.impl.AbstractCacheClient;

public class SpyMemcachedClient extends AbstractCacheClient {

    private MemcachedClient memcachedClient;
    private String appName;

    public SpyMemcachedClient(String appName, PoolConfig poolConfig) {
        ArrayList<InetSocketAddress> addrs = new ArrayList<InetSocketAddress>();

        for (String server : poolConfig.getServers()) {
            int finalColon = server.lastIndexOf(':');
            if (finalColon < 1) {
                throw new IllegalArgumentException("Invalid server " + server);
            }

            String hostPart = server.substring(0, finalColon);
            String portNum = server.substring(finalColon + 1);

            addrs.add(new InetSocketAddress(hostPart, Integer.parseInt(portNum)));
        }

        if (!poolConfig.isFailOver())
            throw new IllegalArgumentException("FailOver");
        try {
            if (poolConfig.isCabinet()) {
                this.memcachedClient = new MemcachedClient(new DefaultTyrantConnectionFactory(), addrs);
            } else
                this.memcachedClient = new MemcachedClient(addrs);
        } catch (IOException e) {
            this.log.error(String.format("appName[%s]", new Object[] {appName}), e);
        }

        this.appName = appName;
    }

    @Override
    public boolean add(String key, Object value, Date date) {
        Future<Boolean> rs = this.memcachedClient.add(getKey(key), getExpire(date), value);
        boolean v = false;
        try {
            v = rs.get().booleanValue();
        } catch (Exception e) {
            this.log.error(String.format("add error,key[%s]", new Object[] {key}), e);
        }
        return v;
    }

    @Override
    public long addOrDecr(String key, long value) {
        return this.memcachedClient.decr(getKey(key), (int) value, value);
    }

    @Override
    public long addOrIncr(String key, long value) {
        return this.memcachedClient.incr(getKey(key), (int) value, value);
    }

    @Override
    public long decr(String key, long value) {
        return this.memcachedClient.decr(getKey(key), (int) value);
    }

    @Override
    public boolean delete(String key) {
        Future<Boolean> rs = this.memcachedClient.delete(getKey(key));
        boolean v = false;
        try {
            v = rs.get().booleanValue();
        } catch (Exception e) {
            this.log.error(String.format("delete error,key[%s]", new Object[] {getKey(key)}), e);
        }
        return v;
    }

    @Override
    public boolean flushAll() {
        Future<Boolean> f = this.memcachedClient.flush();
        boolean v = false;
        try{
            v = f.get().booleanValue();
        }catch(Exception e){
            log.error("flushall error",e);
        }
        return v;
    }

    @Override
    public boolean flushAll(String[] keys) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        try {
            return (T) this.memcachedClient.get(getKey(key));
        } catch (ClassCastException e) {
            this.log.error(String.format("Class cast error, key[%s]", new Object[] {key}), e);
        } catch (Exception e) {
            this.log.error(String.format("get from cache error, key[%s]", new Object[] {key}), e);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Map<String, T> getMulti(String[] groups) {
        try {
            Map<String, Object> map = this.memcachedClient.getBulk(getKeys(groups));
            Map<String, T> newMap = new HashMap<String, T>(map.size());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = getOriginKey((String) entry.getKey());
                newMap.put(key, (T) entry.getValue());
            }
            return newMap;
        } catch (ClassCastException e) {
            this.log.error("Class cast error, key[%s]", getKeys(groups), e);
        }
        return new HashMap<String, T>(1);
    }

    @Override
    public long incr(String key, long value) {
        return this.memcachedClient.incr(getKey(key), (int) value);
    }

    @Override
    public boolean replace(String key, Object value, Date date) {
        Future<Boolean> rs = this.memcachedClient.replace(getKey(key), getExpire(date), value);
        boolean v = false;
        try {
            v = rs.get().booleanValue();
        } catch (Exception e) {
            this.log.error(String.format("replace error,key[%s]", new Object[] {getKey(key)}), e);
        }
        return v;
    }

    @Override
    public boolean set(String key, Object value, Date expiry) {
        Future<Boolean> rs = set2(key, value, expiry);
        boolean v = false;
        try {
            v = rs.get().booleanValue();
        } catch (Exception e) {
            this.log.error(String.format("set error,key[%s]", new Object[] {key}), e);
        }
        return v;
    }

    public Future<Boolean> set2(String key, Object value, Date expiry) {
        Future<Boolean> rs = this.memcachedClient.set(getKey(key), getExpire(expiry), value);
        return rs;
    }

    @Override
    public void setCompressEnable(boolean enable) {
    }

    @Override
    public void setCompressThreshold(long num) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDefaultEncoding(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String sanitizeKey(String key) {
        return key;
    }

    @Override
    public void shutdown() {
        this.memcachedClient.shutdown();
    }

    private int getExpire(Date expire) {
        if (null == expire)
            return 0;
        return (int) expire.getTime() / 1000;
    }

    private String getKey(String key) {
        if ((null == this.appName) || ("".equals(this.appName))) {
            return key;
        }
        return String.format("%s-%s", new Object[] {this.appName, key});
    }

    private String getOriginKey(String key) {
        int index = key.indexOf("-");
        return key.substring(index + 1);
    }

    private String[] getKeys(String[] keys) {
        String[] newKeys = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            newKeys[i] = getKey(keys[i]);
        }
        return newKeys;
    }

}
