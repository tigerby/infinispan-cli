package com.tigerby.infinispan.client;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

/**
 * <p> title here </p>
 *
 * @author <a href="mailto:bongyeonkim@gmail.com">Kim Bryan</a>
 * @version 1.0
 */
public class Cache {
    private RemoteCacheManager remoteCacheManager;

    public Cache() {
        remoteCacheManager = new RemoteCacheManager();
    }

    public Cache(String servers) {
        remoteCacheManager = new RemoteCacheManager(servers);
    }

    public RemoteCache getCache(String cacheName) {
        return remoteCacheManager.getCache(cacheName);
    }
}
