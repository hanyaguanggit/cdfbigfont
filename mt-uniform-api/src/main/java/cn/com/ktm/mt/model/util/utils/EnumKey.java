package cn.com.ktm.mt.model.util.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类的辅助工具<br>
 * 用于 valueOf(K key) 方法的快速实现<br>
 * 
 
 * @Description  
 * @version      
 *
 * @param <T>
 */
public abstract class EnumKey<K, T> {
    
    Map<K, T> map = new HashMap<>();
    
    protected abstract K getKey(T enumOje);
    
    /**
     * key 不能为空，也不能重复！
     * @param enumOje
     */
    public void addEnum(T enumOje) {
        if (enumOje == null) {
            throw new NullPointerException("Enum is NULL");
        }
        
        K key = getKey(enumOje);
        if (key == null) {
            throw new NullPointerException("Enum.key is NULL : " + enumOje);
        }
        
        if (map.containsKey(key)) {
            throw new RuntimeException("Enum.key is Repeated : key=" + key + ", but Enum is " + map.get(key) + " and " + enumOje);
        }
        
        map.put(key, enumOje);
    }
    
    public T getEnum(K key) {
        if (key == null) {
            return null;
        }
        return map.get(key);
    }
    
}
