package cn.com.ktm.mt.model.util.utils;

@FunctionalInterface
public interface ObjKey<K, V> {

	K groupKeyForObject(V v);
	
}
