package cn.com.ktm.mt.model.util.utils;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 接口参数验证
 * Created by gaocl on 16-3-2.
 */
public class ValidUtil {

    /**
     * 护照校验
     *
     * @param str
     * @return
     */
    public static boolean isPassport(String str) {
        //TODO 护照验证
        String regExp = "^((P|p)\\d{7})|((G|g)\\d{8})$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str.trim());
        return m.matches();
    }

    /**
     * 身份证校验
     *
     * @param str
     * @return
     */
    public static boolean isIdCard(String str) {
        //TODO 身份证校验
        String result = null;
        try {
            result = IDCard.IDCardValidate(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return "".equals(result) ? true : false;
    }

    /**
     * 密码验证
     *
     * @param pwd
     * @return
     */
    public static boolean isRightPwd(String pwd) {
        // 类似 aB34567!
        //要求密码长度最少6位，包含至少1个特殊字符，1个数字，1个大写字母和一些小写字母
//        String regExp ="(?=^.{6,11}$)(?=(?:.*?\\d){1})(?=.*[a-z])(?=(?:.*?[A-Z]){1})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\\s)[0-9a-zA-Z!@#$%*()_+^&]*$"; //字母+数字+特殊字符
//        String regExp ="^(?!(?:[^a-zA-Z]+|\\D|[a-zA-Z0-9])$).{6,11}$"; //字母+数字
        String regExp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,16}$"; //字母+数字
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(pwd.trim());
        return m.matches();
    }

    /**
     * 手机号验证
     *
     * @param mobile 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     *               联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
     * @return
     */
    public static boolean isMobile(String mobile) {
        boolean flag = false;
        try{
           // String regExp = "^((177)|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
           // String regExp = "/^1[3|4|5|6|7|8][0-9]\\d{8}$/";//16是声讯号
           String regExp = "^1[3|4|5|7|8][0-9]\\d{8}$";// 147
           Pattern p = Pattern.compile(regExp);
           mobile = mobile.replaceAll(" ", "");
           Matcher m = p.matcher(mobile.trim());
           return m.matches();
          }catch(Exception e){
    	     flag = false;
    	  }
       return  flag;
       }

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     *
     * @return boolean
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
//	    String check = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
            String  check   = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex   = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    public static boolean isEmptyList(List lst) {
        if (lst != null && lst.size() > 0) return false;
        return true;
    }

    public static boolean isNotEmptyList(List lst) {
        if (lst != null && lst.size() > 0) return true;
        return false;
    }

    public static boolean isNullStr(String str) {
        if (str == null) return true;
        str = str + "";
        return "".equals(str) || "null".equals(str.toLowerCase()) || "[]".equals(str) || str.length() == 0;
    }

    public static boolean orgcodeValidate(String code) {
        if (code == null || code.trim().equals("")) {
            return false;
        }

        String[] values = code.toUpperCase().split("-");
        int[] ws = {3, 7, 9, 10, 5, 8, 4, 2};
        String endWord = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String regExp = "^([0-9A-Z]){8}$";// 147
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(values[0]);
        if (!m.matches()) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            sum += endWord.indexOf(values[0].charAt(i)) * ws[i];
        }
        int C9 = 11 - (sum % 11);
        String YC9 = values[1];
        String C9Str = "";
        if (C9 == 11) {
            C9Str = "0";
        } else if (C9 == 10) {
            C9Str = "X";
        }else{
            C9Str = ""+C9;
        }
        return C9Str.equals(YC9);
    }

    public static boolean uniformSocialCreditCodeValidate(String code) {
        if (code == null || code.trim().equals("")) {
            return false;
        }
        String check = "[^_IOZSVa-z\\W]{2}\\d{6}[^_IOZSVa-z\\W]{10}";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(code.toUpperCase());
        return matcher.matches();
    }

}
