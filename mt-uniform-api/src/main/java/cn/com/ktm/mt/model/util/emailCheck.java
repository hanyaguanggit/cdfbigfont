package cn.com.ktm.mt.model.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
　@Author:gaopeng
  @Description:
  @Date:Creted in 11:37　2019/9/30/030　　
  　　　　　　　
*/
public class emailCheck{

    //判断Email合法性
    public static boolean isEmail(String string) {
        if (string == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }
}
