package cn.com.ktm.mt.model.util.utils;


/* 生成卡券号
 * 总共20位  9位ID号，保证编号的唯一性。开头2位，中间4位，结尾4位。 
 * 11位随机数，每一位都10以内的随机数生成。每一位都单独生成， 保证不可预测性。
 * 批次号ID的后5位(5位）+5位序列号+10位随机数
 * 01342323232 1035 2432 2630 4647 
 * 
 * */
public class CouponNOGenerator {
    public static final int digitDomain = 10;

    public String generate() {
         /* 生成10位10以内的随机数,组成的串  */
        String couponNo = "";
        int i = 0;
        while (i < 10) {
            int n = (int) (Math.random() * digitDomain);
            couponNo += n;
            i++;
        }
        return couponNo;
    }
}

