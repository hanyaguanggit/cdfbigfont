package cn.com.ktm.mt.model.util.utils;

/**
 * 常量类
 * Created by gaocl on 16-3-2.
 */
public class Constant {


    public static final String ENCODE = "UTF-8";
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    
    public static final String INTERVAL_FIRST = "9:30";//分时段入馆统计 第一个时间
    public static final long  INTERVAL_MILLI=30*60*1000;//3O分钟  分时段入馆统计间隔 
    public static final String INTERVAL_END = "17:00";//分时段入馆统计最后一个时间
    



    /**
     * <p>
     * 日入馆人群统计分析
     * 年龄分段 类型代码 <br>
     * 1 0~6岁 <br>
     * 2  7~22岁  <br>
     * 3  23~59岁  <br>
     * 4  60~岁  <br>
     * </p>
     */
    public static final Integer[] USER_AGE_TYPE= new Integer[]{1,2,3,4};
    /**
     * 和 USER_AGE_TYPE对应
     */
    public static final String[] USER_AGE_TYPE_DECR= new String[]{
    	          "0~6岁","7~22岁","23~59岁","60岁及以上"};
    
    /**管理员重置密码，默认密码*/
    public static final String DEFAULT_ADMIN_PWD="123456a";
}