package cn.com.ktm.mt.model.util.utils;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.text.ParseException;
import java.util.*;


/**
 * String工具类<br/>
 * 非空验证
 *
 * @author July july_sky@foxmail.com
 * @version 1.0
 * @date 2015年4月3日下午1:20:39
 * @Copyright ©2003-2015 北京春秋永乐文化传播有限公司. All Rights Reserved.
 */
public class StringUtils  {
    private static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    /**
     * 验证Collection是否为空
     *
     * @param coll
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年4月3日下午1:26:17
     */
    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.size() <= 0;
    }

    /**
     * 验证Collection是否不为空
     *
     * @param coll
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年4月3日下午1:26:17
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 验证Map是否为空
     *
     * @param coll
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年4月3日下午1:26:17
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() <= 0;
    }

    /**
     * 验证Map是否不为空
     *
     * @param coll
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年4月3日下午1:26:17
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 验证Object是否为空
     *
     * @param obj
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年4月3日下午1:31:37
     */
    public static boolean isBlank(Object obj) {
        return obj == null || isBlank(obj.toString());
    }

    /**
     * 验证String是否为空
     *
     * @param obj
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年4月3日下午1:31:37
     */
    public static boolean isBlank(String str) {
        return str == null ||  ValidUtil.isNullStr(str);
    }

    //	private static final String COUNT_SQL_REG = "select\\s+.*\\s+from\\s";
    private static final String COUNT_SQL_REG = "^\\s*?(select\\s+([\\s\\S]*?)\\s+)?from\\s+[\\s\\S]*?(\\sorder\\s[\\s\\S]*)?";
    private static final String COUNT_REPLACE = "select count(1) from ";

    /**
     * 根据原始SQL获取countSQl
     *
     * @param querySql
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年4月17日上午8:59:07
     */
    public static String getCountSql(String querySql) {
        return querySql.toLowerCase().replaceAll(COUNT_SQL_REG, COUNT_REPLACE).replace("\t\n", " ");
    }

    /**
     * 根据原始SQL获取countSQL，不截取
     *
     * @param querySql
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年5月4日上午9:01:37
     */
    public static String getCountSqlChild(String querySql) {
        return COUNT_REPLACE.concat("(").concat(querySql).concat(") t");
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 用指定表达式格式化字符串
     *
     * @param str 要格式化的字符串
     * @param rgx 替换表达式
     * @param args 要填充的参数
     * @return
     */
    /*public static String format(String str,String rgx,String ...args) {
		if(isBlank(args) || isBlank(str) || args.length <= 0) {return str;}
		for(String s : args) {
			str = str.replaceFirst(rgx, s);
		}
		return str;
	}*/

    /**
     * 用指定表达式格式化字符串
     *
     * @param str  要格式化的字符串
     * @param rgx  替换表达式
     * @param args 要填充的参数
     * @return
     */
    public static String format(String str, String rgx, Object... args) {
        if (isBlank(args) || isBlank(str) || args.length <= 0) {
            return str;
        }
        for (Object s : args) {
            if (s != null) {
                str = str.replaceFirst(rgx, s.toString());
            }
        }
        return str;
    }


    /**
     * 计算表达式的值
     *
     * @param str
     * @param def
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T expEval(String str, T... def) {
        try {
            Object obj = jse.eval(str);
            if (!StringUtils.isBlank(obj)) {
                return (T) obj;
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        if (def != null && def.length > 0) {
            return def[0];
        }
        return null;
    }

    /**
     * 根据全路径获取文件名称
     *
     * @param allPath
     * @return
     * @author July july_sky@foxmail.com
     * @date 2015年9月21日下午10:04:22
     */
    public static String getFileName(Object allPath) {
        String _path = StringUtils.isBlank(allPath) ? "" : allPath.toString();
        return _path.lastIndexOf("/") == -1 ? _path : _path.substring(_path.lastIndexOf("/") + 1, _path.length());
    }
    
    /**
     * 获取后几位字符串
     * @param str
     * @param number
     * @return
     */
    public static String getFromLast(String str,int number) {
        return str.substring(str.length()-number, str.length());
    }

    public static List<String> splitToStringList(String data,String regex){
        if(data !=null ){
            List<String> list = new ArrayList<String>();
            String[] dataArr= data.split(regex);
            for(String str : dataArr){
                list.add(str);
            }
            return list;
        }else{
            return new ArrayList<String>();
        }
    }

    public static List<Integer> splitToIntegerList(String data,String regex){
        if(data !=null ){
            List<Integer> list = new ArrayList<Integer>();
            String[] dataArr= data.split(regex);
            for(String str : dataArr){
                list.add(Integer.valueOf(str));
            }
            return list;
        }else{
            return new ArrayList<Integer>();
        }
    }

    public static List<Long> splitToLongList(String data,String regex){
        if(data !=null ){
            List<Long> list = new ArrayList<Long>();
            String[] dataArr= data.split(regex);
            for(String str : dataArr){
                list.add(Long.valueOf(str));
            }
            return list;
        }else{
            return new ArrayList<Long>();
        }
    }

    /**
     *  文件操作 获取文件扩展名
     * @param filename
     * @return 文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return "";
    }

//    /**
//     * 判断是否同为扩展名
//     *
//     * @param fileName
//     * @param ext
//     * @return
//     */
//    public static boolean isExtension(String fileName, String ext) {
//        return StringUtils.equalsIgnoreCase(getExtensionName(fileName), ext);
//    }
//    
    /**
     * 不够位数的在前面补0，保留code的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String num,Integer code) { 
        String result = "";
        // 保留code的位数    
        String d ="%0" + num + "d";
        result = String.format(d, code);
        return result;
    }
    
    public static void main(String[] args) throws ParseException {
    	System.out.println(String.format("%09s","123"));

    }
    
}
