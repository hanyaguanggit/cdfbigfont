package cn.com.ktm.mt.model.util.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author       wyf
 * @date         2017年9月8日 上午11:51:11
 * @Description  
 * @version
 */
public class IntervalUtil {
    
    /**
     * zhe function from IntervalInDateReprotService 
     * @param beginTime
     * @return
     */
    public static List<Long> getIntervalTimes(long beginTime){
        List<Long>  times = new ArrayList<Long>(); 
        times.add(beginTime);
        long firstEndTime = beginTime+DateUtils.timeToInt(Constant.INTERVAL_FIRST);
        times.add(firstEndTime);
        long intervalEnd = beginTime+DateUtils.timeToInt(Constant.INTERVAL_END);
        
        long before = firstEndTime;
        while((intervalEnd-before)>=Constant.INTERVAL_MILLI){
            before=before+Constant.INTERVAL_MILLI;
            times.add(before);
        }
        return times;
    }
}

