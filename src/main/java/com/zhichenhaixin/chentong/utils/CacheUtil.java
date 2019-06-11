package com.zhichenhaixin.chentong.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * 
 * 缓存的工具类
 * 
 * @author pwl
 *
 */
public class CacheUtil {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtil.class);

	private CacheUtil() {
	}

	public static Cache getECache(String cache_name) {
		boolean b = CacheManager.getInstance().cacheExists(cache_name);
		Cache cache = null;
		if (b) {
			cache = CacheManager.getInstance().getCache(cache_name);
		}
		return cache;
	}
}
