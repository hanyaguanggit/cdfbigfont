package cn.com.ktm.mt.model.util.utils;

import com.alibaba.fastjson.JSON;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数转换工具类
 * Created by gaocl on 16-4-18.
 */
public class ParamsUtil {


    /**
     * 解析加密参数
     *
     * @param json
     * @param clazz
     */
    public static <T> T getParams(String json, Class<T> clazz) {

        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bean转换map
     *
     * @param bean
     * @return
     */
    public static Map<String, String> getParamsFromBean(Object bean) {

        Map<String, String> returnMap = new HashMap<>();
        try {
            Class clazzType = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazzType);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, String.valueOf(result));
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
            return returnMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }
}
