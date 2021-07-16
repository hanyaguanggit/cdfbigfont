package cn.com.ktm.mt.model.util.utils.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * 订单Service
 * Created by gaocl on 16-3-17.
 */
public class SecurityUtils {

    private static final String key = "chgpwtaiexin+1";

    public static String encrypt(String txtStr) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(txtStr)) {
            return "";
        }
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.";
        String ikey = "-x6g6ZWm2G9g_vr0Bo.pOq3kRIxsZ6rm";
        int nh1 = (int) (Math.random() * 64);
        int nh2 = (int) (Math.random() * 64);
        int nh3 = (int) (Math.random() * 64);
        String ch1 = chars.substring(nh1, nh1 + 1);
        String ch2 = chars.substring(nh2, nh2 + 1);
        String ch3 = chars.substring(nh3, nh3 + 1);
        int nhnum = nh1 + nh2 + nh3;
        int knum = 0, i = 0;
        if (StringUtils.isEmpty(txtStr) && key.length() > 0) {
            char[] keys = key.toCharArray();
            for (char c : keys) {
                int ascii = (int) c;
                knum += ascii;
            }
        }
        String md51 = DigestUtils.md5Hex(key + ch1);
        String md52 = DigestUtils.md5Hex(md51 + ch2 + ikey);
        String md53 = DigestUtils.md5Hex(md52 + ch3);
        String mdKey = md53.substring(nhnum % 8, nhnum % 8 + knum % 8 + 16);

        String txt = new String(Base64.encodeBase64(txtStr.getBytes("UTF-8")));//用apache的commons包 commons-codec-1.7.jar Base64加密
        txt = txt.replace("+", "-").replace("/", "_").replace("=", ".");
        String tmp = "";
        int j = 0, k = 0;
        char[] textchars = txt.toCharArray();
        char[] mdKeychars = mdKey.toCharArray();
        char[] charsStrs = chars.toCharArray();
        for (i = 0; i < textchars.length; i++) {
            k = k == mdKeychars.length ? 0 : k;

            int postxt = chars.indexOf(textchars[i]);
            int mkascii = mdKeychars[k++];
            j = (nhnum + postxt + mkascii) % 64;
            tmp += charsStrs[j];
        }
        int tmplen = tmp.length();

        int pos1 = nh2 % (++tmplen);
        int pos2 = nh1 % (++tmplen);
        int pos3 = knum % (++tmplen);

        tmp = insertStr(tmp, ch3, pos1);
        tmp = insertStr(tmp, ch2, pos2);
        tmp = insertStr(tmp, ch1, pos3);

        return tmp;
    }

    public static String decrypt(String txtStr, int ttl) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(txtStr)) {
            return txtStr;
        }

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.";
        String ikey = "-x6g6ZWm2G9g_vr0Bo.pOq3kRIxsZ6rm";
        int knum = 0, i = 0;
        int tlen = txtStr.length();
        if (StringUtils.isEmpty(txtStr) && key.length() > 0) {
            char[] keys = key.toCharArray();
            for (char c : keys) {
                int ascii = (int) c;
                knum += ascii;
            }
        }

        String ch1 = txtStr.substring(knum % tlen, knum % tlen + 1);
        int nh1 = chars.indexOf(ch1);
        txtStr = updateStr(txtStr, "", knum % tlen--);

        String ch2 = txtStr.substring(nh1 % tlen, nh1 % tlen + 1);
        int nh2 = chars.indexOf(ch2);
        txtStr = updateStr(txtStr, "", nh1 % tlen--);

        String ch3 = txtStr.substring(nh2 % tlen, nh2 % tlen + 1);
        int nh3 = chars.indexOf(ch3);
        txtStr = updateStr(txtStr, "", nh2 % tlen--);

        int nhnum = nh1 + nh2 + nh3;
        String md51 = DigestUtils.md5Hex(key + ch1);
        String md52 = DigestUtils.md5Hex(md51 + ch2 + ikey);
        String md53 = DigestUtils.md5Hex(md52 + ch3);
        String mdKey = md53.substring(nhnum % 8, nhnum % 8 + knum % 8 + 16);

        String tmp = "";
        int j = 0, k = 0;
        char[] textchars = txtStr.toCharArray();
        char[] mdKeychars = mdKey.toCharArray();
        char[] charsStrs = chars.toCharArray();
        for (i = 0; i < textchars.length; i++) {
            k = k == mdKeychars.length ? 0 : k;
            int postxt = chars.indexOf(textchars[i]);
            int mkascii = (int) mdKeychars[k++];
            j = postxt - nhnum - mkascii;
            while (j < 0) {
                j += 64;
            }
            tmp += charsStrs[j];
        }
        tmp = tmp.replace("-", "+").replace("_", "/").replace(".", "=");
        tmp = new String(Base64.decodeBase64(tmp.getBytes("UTF-8")));//用apache的commons包 commons-codec-1.7.jar Base64加密
        return tmp;
    }

    private static String insertStr(String str1, String str2, int p) {
        String startStr = str1.substring(0, p);
        String endStr = str1.substring(p);
        return startStr + str2 + endStr;
    }

    private static String updateStr(String str1, String str2, int p) {
        String startStr = str1.substring(0, p);
        String endStr = str1.substring(p + 1);
        return startStr + str2 + endStr;
    }

    public static void main(String args[]) throws UnsupportedEncodingException {
        //使用方法
        String str = encrypt("123456");
        // String str ="kY2w4f9_lVyOzwgKkqnVmL4nM40bo_G2TDrsa4J7wkChjKlELRjkUtyyYK2xxYzzA9n-G_X3H4IzbyVzBtTr0VWFuHjANArnvx1PtQcgmCK5cjXXqP96kn35Ajv3vdwnACRVdm70FAB2wWbWxbJj_LLlAFujAzMrtO_2E28rh--2hs-3mfw5BtRuEiLnzSI2AtImhN84AwPoeEB4zrv-Qjv3vdwnACRVdmux2kS30nKiEgNxwEOl9IsnwXItu-RqhFCbOZRouvSmzeQltIB3EyLm4T5j8BKo1OGrMkQkvsO4zb89-z_h7F58zLMVdm-x1gSv0eDY0UOzi7LlApv0TwG0hDK3gpEpCuE3NP_z2D_yR9M0wlYpIf62bI-nVy79OsCoekE5fmI8xjH0aMt7UrBjhCAzzAClB3PnkMF3x_f";
        System.out.println(str);
        System.out.println(decrypt(str, 0));

    }
}
