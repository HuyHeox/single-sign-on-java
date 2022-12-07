package com.ks.sso.service;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    MemcacheService memCache = MemcacheServiceFactory.getMemcacheService();

    public void PushToCache(String name, String value) {
        if (memCache != null && memCache.contains(name)) {
            memCache.delete(name);
        }
        memCache.put(name, value, Expiration.byDeltaSeconds(3600));
    }

    public String GetValueFromCache(String name) {
        return (String) memCache.get(name);
    }


    public void DeleteValueInCache(String name) {
        if (memCache != null && memCache.contains(name)) {
            memCache.delete(name);
        }
    }
}
