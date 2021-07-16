//package util.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
////import yles.templatable.framework.core.commom./*SysPropertyKey*/;
//
///**
// * 系统参数工具类
// *
// * @author yangsen
// * @date 2017/4/17 9:53
// * @description
// */
//@Component
//public class SysPropertyUtil {
//
//    private static Logger logger = LoggerFactory.getLogger(SysPropertyUtil.class);
//    
//    private static Map<SysPropertyKey<?>, Object> map = new HashMap<>();
//    
//    /**
//     * 设置参数值
//     * @param key
//     * @param value
//     */
//    public static <T> void setValue(SysPropertyKey<T> key, T value) {
//        
//        boolean isNullOrEmpty = false;
//        
//        if (value == null) {
//            isNullOrEmpty = true;
//        } else if (value instanceof String) {
//            String str = (String) value;
//            isNullOrEmpty = StringUtils.isEmpty(str);
//        }
//        
//        if (isNullOrEmpty) {
//            if (key.isNotNullOrEmpty()) {
//                String errorMessage = StringUtils.format("缺少参数：{}", key.getName());
//                logger.error(errorMessage);
//                throw new RuntimeException(errorMessage);
//            }
//            
//            return;
//        }
//        
//        if (map.containsKey(key)) {
//            String errorMessage = StringUtils.format("重复的参数：{}", key.getName());
//            logger.error(errorMessage);
//            throw new RuntimeException(errorMessage);
//        }
//        
//        map.put(key, value);
//    }
//
//    /**
//     * 获取参数值
//     * 
//     * @param key
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T getValue(SysPropertyKey<T> key) {
//        Object value = map.get(key);
//        if (value == null) {
//            return key.getDefaultValue();
//        }
//        return (T) value;
//    }
//
//}
