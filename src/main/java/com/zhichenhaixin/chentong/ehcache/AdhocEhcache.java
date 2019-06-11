package com.zhichenhaixin.chentong.ehcache;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;


import com.zhichenhaixin.chentong.model.adhoc.AdhocInfo;
import com.zhichenhaixin.chentong.utils.CacheUtil;

/**
 * 自组网船站缓存类
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 */
public class AdhocEhcache {
	
	public static final String CACHE_NAME = "AdhocInfoEhcache";

	public static void put(AdhocInfo adhocInfo) {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return;
		}
		cache.put(new Element(adhocInfo.getAdhocCode(), adhocInfo));
	}

	@SuppressWarnings("rawtypes")
	public static List<AdhocInfo> getAll() {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return null;
		}
		List keys = cache.getKeys();
		int size = keys.size();
		List<AdhocInfo> result = new ArrayList<AdhocInfo>(size);
		for (int i = 0; i < size; i++) {
			Element element = cache.get(keys.get(i));
			if (element == null) {
				continue;
			}
			result.add((AdhocInfo) element.getValue());
		}
		return result;
	}

	/**
	 * 获取所有缓存数据
	 * 
	 * @return Cache
	 */
	public static Cache getAllEhCache() {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return null;
		}
		return cache;
	}

	public static AdhocInfo get(String adhocCode) {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return null;
		}
		Element element = cache.get(adhocCode);
		if (element == null) {
			return null;
		}
		return (AdhocInfo) element.getValue();
	}

	public static void clear() {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return;
		}
		cache.removeAll();
	}

	public static void remove(String adhocCode) {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return;
		}
		cache.remove(adhocCode);
	}
}
