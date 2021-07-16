package cn.com.ktm.mt.model.util.utils;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 类 名: List2Map<br/>
 * 描 述: <br/>
 * 作 者: 姚晓进<br/>
 * 创 建： 2016年3月21日<br/>
 * <p/>
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
public class List2Map<E, T> {

    private static List2Map instance = new List2Map();

    public static List2Map getInstance() {
        return instance;
    }

    /**
     * list转map
     *
     * @param methodName
     * @param list
     * @return
     */
    public Map<E, T> tobeMap(String methodName, List<T> list) {
        Map<E, T> map = new HashMap<E, T>();
        try {
            if (list != null && list.size() > 0) {
                for (T t : list) {
                    Class cls = t.getClass();
                    Method getMethod = cls.getDeclaredMethod(methodName);
                    map.put((E) getMethod.invoke(t), t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * list转map（value为相同key的list）,有序
     *
     * @param methodName
     * @param list
     * @return
     */
    public Map<E, T> tobeLinkedHashMap(String methodName, List<T> list) {
        Map<E, T> map = new LinkedHashMap<E, T>();
        try {
            if (list != null && list.size() > 0) {
                for (T t : list) {
                    Class cls = t.getClass();
                    Method getMethod = cls.getDeclaredMethod(methodName);
                    map.put((E) getMethod.invoke(t), t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * list转map（value为相同key的list）,有序
     *
     * @param methodName
     * @param list
     * @return
     */
    public Map<E, List<T>> tobeLinkedHashMaps(String methodName, List<T> list) {
        Map<E, List<T>> map = new LinkedHashMap<E, List<T>>();
        try {
            if (list != null && list.size() > 0) {
                for (T t : list) {
                    Class cls = t.getClass();
                    Method getMethod = cls.getDeclaredMethod(methodName);
                    Object o = getMethod.invoke(t);
                    List<T> tList = map.get(o);
                    if (tList == null) {
                        tList = new ArrayList<T>();
                    }
                    tList.add(t);
                    map.put((E) o, tList);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<E, List<T>> tobeMaps(String methodName, List<T> list) {
        Map<E, List<T>> map = new HashMap<E, List<T>>();
        try {
            if (list != null && list.size() > 0) {
                for (T t : list) {
                    Class cls = t.getClass();
                    Method getMethod = cls.getDeclaredMethod(methodName);
                    Object o = getMethod.invoke(t);
                    List<T> tList = map.get(o);
                    if (tList == null) {
                        tList = new ArrayList<T>();
                    }
                    tList.add(t);
                    map.put((E) o, tList);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 对象转list
     *
     * @param unit
     * @return
     */
    public List<T> tobeList(final T unit) {
        try {
            return new ArrayList<T>() {
                {
                    add(unit);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * list对象中提取字段的集合
     *
     * @param methodName
     * @param list
     * @return
     */
    public List<E> getFieldList(String methodName, List<T> list) {
        List<E> data = new ArrayList<E>();
        try {
            if (list != null && list.size() > 0) {
                for (T t : list) {
                    Class cls = t.getClass();
                    Method getMethod = cls.getDeclaredMethod(methodName);
                    data.add((E) getMethod.invoke(t));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean isNotEmpty(List list) {
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 字符串转list
     *
     * @param Str      包含,的字符串
     * @param t        Integer.class/String.class
     * @param isDecode 是否url解码
     * @return list的数据对象
     */
    public List<T> splitString(String Str, T t, boolean isDecode) {
        List<T> strList = new ArrayList<T>();
        try {
            if (Str != null) {
                String[] strArry = Str.split(",");
                for (int i = 0; i < strArry.length; i++) {
                    if (t == String.class) {
                        if (isDecode) {
                            strList.add((T) URLDecoder.decode(strArry[i], "UTF-8"));
                        } else {
                            strList.add((T) strArry[i]);
                        }
                    } else if (t == Integer.class) {
                        Integer n = StringUtils.isEmpty(strArry[i]) ? 0 : Integer.valueOf(strArry[i]);
                        strList.add((T) n);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }

    public static void main(String[] args) {
        List2Map list2Map = List2Map.getInstance();
        List list = list2Map.splitString(",401", Integer.class, false);
        System.out.println(list);
    }

    public String list2String(List<Integer> ids) {
        StringBuffer sb = new StringBuffer();
        if (ids != null && !ids.isEmpty()) {
            for (int i = 0; i < ids.size(); i++) {
                sb.append(ids.get(i));
                if (i != ids.size() - 1) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }
}
