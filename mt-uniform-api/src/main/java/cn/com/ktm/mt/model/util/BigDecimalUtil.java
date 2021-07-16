package cn.com.ktm.mt.model.util;

import java.math.BigDecimal;

/**
 * @author zhangdongdong
 * @description:
 * @date created in 11:56. 2018/6/26.
 * @modified by:
 */
public class BigDecimalUtil {

    /**
     * 订单金额转换因子，数据库存的是小数单位是元，接口传输使用整数，单位分，需要相互转换
     * */
    public static final int PRICE_FACTOR= 100;

    private static final BigDecimal PRICE_FACTOR_BIGDECIMAL= new BigDecimal(PRICE_FACTOR);


    /**
     * 金额转换成整数分
     * @param total
     * @return
     */
    public  static Long longValueWithFactor(BigDecimal total){
        if(total == null){
            return null;
        }
        return  total.multiply(PRICE_FACTOR_BIGDECIMAL).longValue();
    }

    public static BigDecimal bigDecimalValueWithFactor(Long total){
        if(total == null){
            return null;
        }
        BigDecimal bigDecimal =  new BigDecimal(total);
        return  bigDecimal.divide(PRICE_FACTOR_BIGDECIMAL);
    }


    public static void main(String[] args) {
        Long aa = longValueWithFactor(new BigDecimal(1.5));
        System.out.println("-->"+ MD5Util.strToMD5("123"));
        System.out.println("-->"+bigDecimalValueWithFactor(aa));
    }
}
