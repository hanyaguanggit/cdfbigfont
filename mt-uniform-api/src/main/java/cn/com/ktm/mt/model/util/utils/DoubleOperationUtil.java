package cn.com.ktm.mt.model.util.utils;
import java.math.BigDecimal;

public class DoubleOperationUtil {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	// 这个类不能实例化
	private DoubleOperationUtil() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */

	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */

	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */

	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */

	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static String formatFloatNumber(double value) {
        if(value != 0.00){
            java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
            return df.format(value);
        }else{
            return "0.00";
        }

	}
	
	/**
	 * <p>Description:
	 * v1/v2
	 *  </p>
	 * @param v1
	 * @param v2
	 * @return
	 * @author zhangdongdong
	 * @date 2016-9-9
	 *
	 */
	 public static double rate (Long v1, Long v2){
	    	v1 = v1 == null ? 0: v1;
	    	v2 = v2 == null ? 0: v2;
	    	if(v2 >0){
	    		return DoubleOperationUtil.div(v1, v2,4);
	    	}else{
	    		return 0.00;
	    	}
	 }
	
	public static String formatDoubleNumber(double value) {
        if(value != 0.00){
            java.text.DecimalFormat df = new java.text.DecimalFormat("########0.00");
            return df.format(value);
        }else{
            return "0.00";
        }

    } 
	/**
	 * <p>Description: 
	 *  v1/v2
	 * </p>
	 * @param v1
	 * @param v2
	 * @return
	 * @author zhangdongdong
	 * @date 2016-9-9
	 *
	 */
	public static String rateAndFormat(Long v1,Long v2){
		   Double  r=DoubleOperationUtil.rate(v1, v2);
	   	   r= DoubleOperationUtil.round(r*100,2);
	   	  return  DoubleOperationUtil.formatDoubleNumber( r);
	}
	
	 /**
     * <p>Description:
     * 环比上升率 (v1-v2)/v1
     *  </p>
     * @param v1
     * @param v2
     * @return
     * @author zhangdongdong
     * @date 2016-9-8
     *
     */
    public static String riseRate(Long v1 ,Long v2){
    	v1= v1==null ? 0:v1;
    	v2= v2==null ?0: v2;
    	 if(v1==0){
    		 if(v2>0){
    			return "-100.00"; 
    		 }else{
    			 return "0.00"; 
    		 }
    	 }else{
    		   double fall=DoubleOperationUtil.round(((v1-v2)/v1.doubleValue()), 4);
    	       fall= DoubleOperationUtil.round(fall*100,2);
    		   return  DoubleOperationUtil.formatDoubleNumber(fall);
    	 }
    }
    
    /**
     * <p>Description:
     * 环比上升率(v1-v2)/v1
     *  </p>
     * @param v1
     * @param v2
     * @return
     * @author zhangdongdong
     * @date 2016-9-8
     *
     */
    public static String riseRate(Double v1 ,Double v2){
    	v1= v1==null ? 0:v1;
    	v2= v2==null ?0: v2;
    	 if(v1==0){
    		 if(v2>0){
    			return "-100.00"; 
    		 }else{
    			 return "0.00"; 
    		 }
    	 }else{
    		  double fall=DoubleOperationUtil.round(((v1-v2)/v1), 4);
    	      fall= DoubleOperationUtil.round(fall*100,2);
    		  return  DoubleOperationUtil.formatDoubleNumber(fall);
    	 }
    }
    
    /**
     * <p>Description:
     * 环比下降率 (v2-v1)/v2
     *  </p>
     * @param v1
     * @param v2
     * @return
     * @author zhangdongdong
     * @date 2016-9-8
     *
     */
    public static String fallRate(Long v1 ,Long v2){
    	v1= v1==null ? 0:v1;
    	v2= v2==null ?0: v2;
    	 if(v2==0){
    		 if(v1>0){
    			return "-100.00"; 
    		 }else{
    			 return "0.00"; 
    		 }
    	 }else{
    		   double fall=DoubleOperationUtil.round(((v2-v1)/v2.doubleValue()), 4);
    	       fall= DoubleOperationUtil.round(fall*100,2);
    		   return  DoubleOperationUtil.formatDoubleNumber(fall);
    	 }
    }
    
    /**
     * <p>Description:
     * 环比下降率 (v2-v1)/v2
     *  </p>
     * @param v1
     * @param v2
     * @return
     * @author zhangdongdong
     * @date 2016-9-8
     *
     */
    public static String fallRate(Double v1 ,Double v2){
    	v1= v1==null ? 0:v1;
    	v2= v2==null ?0: v2;
    	 if(v2==0){
    		 if(v1>0){
    			return "-100.00"; 
    		 }else{
    			 return "0.00"; 
    		 }
    	 }else{
    		  double fall=DoubleOperationUtil.round(((v2-v1)/v2), 4);
    	      fall= DoubleOperationUtil.round(fall*100,2);
    		  return  DoubleOperationUtil.formatDoubleNumber(fall);
    	 }
    }
    
    public static void main(String[] args) {
		System.out.println(DoubleOperationUtil.sub(0.2, 0.1));
	}
}