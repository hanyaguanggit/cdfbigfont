package cn.com.ktm.mt.model.util.utils;



import java.util.UUID;

/**
 * 用来生成一个uuid字符串
 *
 * @author wangfeipeng
 * @email 846705189@qq.com
 * @date 2020-06-09
 */
public class UUIDUtil {

    /**
     * 返回一个字符串，这里会调用UUID来生成
     *
     * @return 返回一个uuid字符串
     */
    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
