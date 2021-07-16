package cn.com.ktm.mt.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * hyg
 */
@Log4j2
public class Hygtest {
    //需求：每月固定还10000，在当月还款日之前分若干次还完款。每次还款额度相差控制在一定范围内。

    /**
     * 分配还款金额 hyg
     * @param totalTax 总金额
     * @param times  还款次数
     * @param scopeTax 差异范围
     * @return 每次待还款金额
     */
    public  List<BigDecimal> distribution (BigDecimal totalTax,int times, int scopeTax){
        //把一定金额随机分配，每次分配的数额差额控制在正负200以内，分完为止。
        BigDecimal  remaining = null;
        Random random = new Random();
        List<BigDecimal> datalist = new ArrayList<>();
        boolean isfirst = true;

        BigDecimal averageNum = totalTax.divide(new BigDecimal(times),2,RoundingMode.DOWN);
        log.info("平均值：{}",averageNum);
        for (int i = 0; i < times; i++) {
           double randonNum = 0.00;
           //产生胜负200范围内的double类型数字
            randonNum =(random.nextDouble() * (scopeTax+scopeTax+1)) - scopeTax;
            String result = String .format("%.2f",randonNum);
            log.info("保留两位小数：{}",result);
            BigDecimal val3 = averageNum.add(new BigDecimal(result));
            if(isfirst){
               remaining = totalTax.subtract(val3);
           }else{
               remaining = remaining.subtract(val3);
           }
            datalist.add(val3);
          if(remaining.compareTo(val3) < 0 && remaining.compareTo(new BigDecimal(0)) > 0) {
              log.info("remaining={},分配数额={}",remaining,val3);
              datalist.add(remaining);
              break;
          }
            isfirst = false;
        }
        log.info("分配的数据列表：{}",datalist);
        BigDecimal total = new BigDecimal(0);
        for (BigDecimal val: datalist) {
            total = total.add(val);
        }
        log.info("测试数据相加={}",total);
        return datalist;
    }

    public static void main(String[] args) {
        Hygtest hygtest = new Hygtest();
        hygtest.distribution(new BigDecimal(10000),5,100);

       //①、 1100.63, 1098.31, 913.80, 1019.62, 1067.19, 998.85, 950.63, 978.59, 911.23, 1087.00, -125.85

        //②、分配的数据列表：[1000.18, 1033.06, 996.56, 1050.28, 994.11, 948.34, 943.51, 1086.68, 918.71, 1039.57]
        //测试数据相加=10011.00

       // execute(1000,7,200);

    }
}
