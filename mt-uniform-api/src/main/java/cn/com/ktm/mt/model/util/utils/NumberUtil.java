package cn.com.ktm.mt.model.util.utils;



import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;

/**
 * Created by gaocl on 16-3-28.
 */
public class NumberUtil {
    private static final Logger logger = Logger.getLogger(NumberUtil.class);
    /**
     * DecimalFormat转换最简便
     */
    public static String round(Double num) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(num);
    }

    public static String round3(Double num) {
        DecimalFormat df = new DecimalFormat("#0.000");
        return df.format(num);
    }

    public static int string2Int(String numberString){
        int result=-1;
        if(StringUtils.isEmpty(numberString)){
            return result;
        }
        try {
            result=Integer.parseInt(numberString);
        }catch (Exception e){
            logger.error("数字转换异常："+e);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(NumberUtil.round3(0.01));
    }
}
