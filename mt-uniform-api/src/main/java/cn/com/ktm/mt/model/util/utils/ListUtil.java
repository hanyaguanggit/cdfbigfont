package cn.com.ktm.mt.model.util.utils;

import java.util.List;

/**
 * Created by gaocl on 16-3-28.
 */
public class ListUtil {

//    public static String listToString(List<Object> lst) {
//        StringBuffer sb = new StringBuffer();
//        for (Object obj : lst) {
//            sb.append(obj.toString() + ",");
//        }
//        return sb.substring(0, sb.length() - 1);
//    }
    
	/**
	 * 作 者：姚晓进<br/>
	 * @param lst
	 * @return
	 */
    public static <T> String listToString(List<T> lst) {
        StringBuffer sb = new StringBuffer();
        for (T t : lst) {
            sb.append(t.toString() + ",");
        }
        if(lst.size()==0){
        	return "";
        }
        return sb.substring(0, sb.length() - 1);
    }
    
    
}
