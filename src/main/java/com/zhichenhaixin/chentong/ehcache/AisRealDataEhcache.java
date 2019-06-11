package com.zhichenhaixin.chentong.ehcache;

import java.util.ArrayList;
import java.util.List;

import com.zhichenhaixin.chentong.model.ais.AisRealData;
import com.zhichenhaixin.chentong.utils.CacheUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * AIS实时数据缓存类
 * @version V1.0
 * @author pwl
 * @date 2019年5月11日9:13:3
 * @Description 
 */
public class AisRealDataEhcache {
	
	public static final String CACHE_NAME = "AisRealDataEhcache";

	public static void put(AisRealData ais) {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return;
		}
		cache.put(new Element(ais.getMmsi(), ais));
	}

	@SuppressWarnings("rawtypes")
	public static List<AisRealData> getAll() {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return null;
		}
		List keys = cache.getKeys();
		int size = keys.size();
		List<AisRealData> result = new ArrayList<AisRealData>(size);
		for (int i = 0; i < size; i++) {
			Element element = cache.get(keys.get(i));
			if (element == null) {
				continue;
			}
			result.add((AisRealData) element.getValue());
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

	public static AisRealData get(String mmsi) {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return null;
		}
		Element element = cache.get(mmsi);
		if (element == null) {
			return null;
		}
		return (AisRealData) element.getValue();
	}

	public static void clear() {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return;
		}
		cache.removeAll();
	}

	public static void remove(String mmsi) {
		Cache cache = CacheUtil.getECache(CACHE_NAME);
		if (cache == null) {
			return;
		}
		cache.remove(mmsi);
	}
}
