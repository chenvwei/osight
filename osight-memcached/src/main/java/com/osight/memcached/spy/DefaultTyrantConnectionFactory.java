package com.osight.memcached.spy;

import net.spy.memcached.DefaultConnectionFactory;
import net.spy.memcached.transcoders.SerializingTranscoder;
import net.spy.memcached.transcoders.Transcoder;

public class DefaultTyrantConnectionFactory extends DefaultConnectionFactory {
	public Transcoder<Object> getDefaultTranscoder() {
		return new SerializingTranscoder();
	}
}
