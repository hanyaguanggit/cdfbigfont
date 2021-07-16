package cn.com.ktm.mt.model.util.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.util.StringUtils;

/**
 * Collection 工具类
 * 
 * @author       wyf
 * @date         2017年2月4日 下午4:16:58
 * @Description  
 * @version      
 *
 */
public class CollectionUtil {

    public static boolean isEmptyCollection(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean notEmptyCollection(Collection<?> collection) {
        return !isEmptyCollection(collection);
    }

    public static boolean isEmptyCollectionAll(Collection<?> collection) {
        if (isEmptyCollection(collection)) {
            return true;
        }
        else {
            int count = 0;
            for (Object object : collection) {
                if (object instanceof String) {
                    if (StringUtils.isEmpty((String)object)) {
                        count++;
                    }
                } else {
                    if (object == null) {
                        count++;
                    }
                }
            }

            if (count == collection.size()) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    public static List<Long> splitToLongList(String param) {
        return splitToLongValues(param, new ArrayList<Long>());
    }

    public static Set<Long> splitToLongSet(String param) {
        return splitToLongValues(param, new TreeSet<Long>());
    }
    
    private static <T extends Collection<Long>> T splitToLongValues(String param, T list) {
        if (StringUtils.isEmpty(param)) {
            return list;
        } else {
            param = param.trim();
        }
        for (String str : param.split(",")) {
            if (!StringUtils.isEmpty(str)) {
                list.add(new Long(str.trim()));
            }
        }
        return list;
    }

    public static Set<String> splitToSet(String param) {
        Set<String> set = new HashSet<String>();
        if (StringUtils.isEmpty(param)) {
            return set;
        } else {
            param = param.trim();
        }
        for (String str : param.split(",")) {
            if (!StringUtils.isEmpty(str)) {
                set.add(str.trim());
            }
        }
        return set;
    }

    public static Set<Integer> splitToIntSet(String param) {
        Set<Integer> set = new TreeSet<Integer>();
        if (StringUtils.isEmpty(param)) {
            return set;
        } else {
            param = param.trim();
        }
        for (String str : param.split(",")) {
            if (!StringUtils.isEmpty(str)) {
                set.add(new Integer(str.trim()));
            }
        }
        return set;
    }

    public static String concat(Collection<?> data) {
        StringBuilder strBuilder = new StringBuilder();
        if (!isEmptyCollection(data)) {
            boolean isFirst = true;
            for (Object obj : data) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    strBuilder.append(',');
                }
                strBuilder.append(obj);
            }
        }
        return strBuilder.toString();
    }
    
    public static String reconcatIdsStr(String param) {
        String defaultValue = null;
        return reconcatIdsStr(param, defaultValue);
    }
    
    public static String reconcatIdsStr(String param, String defaultValue) {
        if (StringUtils.isEmpty(param)) {
            return defaultValue;
        }
        Set<Integer> ids = splitToIntSet(param);
        if (ids.isEmpty()) {
            return defaultValue;
        }
        return concat(ids);
    }
    
    /**
     * 将集合中的数据 count 分成不同的组。
     * @param data
     * @param count 每个组中的数量
     * @return
     */
    public static <T> List<List<T>> groupByCount(Collection<T> data, int count) {
        if (isEmptyCollection(data)) {
            return new ArrayList<>();
        }
        
        List<List<T>> all = new ArrayList<>();
        
        if (data.size() <= count) {
            all.add(new ArrayList<>(data));
            return all;
        }
        
        List<T> list = new ArrayList<T>(count);
        for (T t : data) {
            list.add(t);
            if (list.size() == count) {
                all.add(list);
                list = new ArrayList<T>(count);
            }
        }
        if (list.size() > 0) {
            all.add(list);
        }
        
        return all;
    }
    
    public static <V> List<List<List<V>>> groupByKey(Collection<V> list, ObjKey<?, V> adapter1, ObjKey<?, V> adapter2) {
        List<List<List<V>>> data = new ArrayList<>();
        if (isEmptyCollection(list)) {
            return data;
        }
        
        List<List<V>> list1 = groupByKey(list, adapter1);
        for (List<V> subList : list1) {
            data.add(groupByKey(subList, adapter2));
        }
        
        return data;
    }
    
    public static <V> List<List<V>> groupByKey(Collection<V> list, ObjKey<?, V> adapter) {
        List<List<V>> data = new ArrayList<>();
        if (isEmptyCollection(list)) {
            return data;
        }
        Map<?, List<V>> map = toMapList(list, adapter);
        for (Object key : map.keySet()) {
            data.add(map.get(key));
        }
        return data;
    }

    public static <K, V> Map<K, V> toMap(Collection<V> list, ObjKey<K, V> adapter) {
        Map<K, V> map = new HashMap<K, V>();
        
        if (isEmptyCollection(list)) {
            return map;
        }
        
        for (V obj : list) {
            K key = adapter.groupKeyForObject(obj);
            map.put(key, obj);
        }
        return map;
    }
    
    public static <K, V> Map<K, List<V>> toMapList(Collection<V> list, ObjKey<K, V> adapter) {
        Map<K, List<V>> map = new HashMap<K, List<V>>();
        
        if (isEmptyCollection(list)) {
            return map;
        }
        
        for (V obj : list) {
            addValueByKey(map, obj, adapter);
        }
        return map;
    }
    
    public static <K, V> void addValueByKey(Map<K, List<V>> map, V value, ObjKey<K, V> adapter) {
        K key = adapter.groupKeyForObject(value);
        addValue(map, value, key);
    }
    
    public static <K, V> void addValue(Map<K, List<V>> map, V value, K key) {
        List<V> list = map.get(key);
        
        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);
        }

        list.add(value);
    }
    
    public static <T> List<T> sublist(List<T> list, int start, int count) {
        if (isEmptyCollection(list)) {
            return new ArrayList<T>(0);
        }
        int size = list.size();
        if (start > size-1) {
            return new ArrayList<T>(0);
        }
        int end = start + count;
        if (end > size) {
            end = size;
        }
        if (start > end) {
            return new ArrayList<T>(0);
        }
        return new ArrayList<T>(list.subList(start, end));
    }
    
    public static <K, V> List<K> toListByKey(Collection<V> list, ObjKey<K, V> adapter) {
        List<K> listVO = new ArrayList<K>();
        if (isEmptyCollection(list)) {
            return listVO;
        }
        for (V obj : list) {
            K key = adapter.groupKeyForObject(obj);
            listVO.add(key);
        }
        return listVO;
    }
}
