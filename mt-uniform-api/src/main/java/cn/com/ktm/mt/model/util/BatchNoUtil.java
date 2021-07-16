package cn.com.ktm.mt.model.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 高鹏
 * @date: 2019/10/17/017
 */
public class BatchNoUtil {
    private static final String SERIAL_NUMBER = "GG"; // 流水号格式
    private static BatchNoUtil batchNoUtil = null;
    private BatchNoUtil() {}
    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static BatchNoUtil getInstance() {
        if (batchNoUtil == null) {
            synchronized (BatchNoUtil.class) {
                if (batchNoUtil == null) {
                    batchNoUtil = new BatchNoUtil();
                }
            }
        }
        return batchNoUtil;
    }
    /**
     * 生成下一个编号
     */
    public synchronized String BatchNoNextNumber(String sno) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (sno == null) {
            id = formatter.format(date) + "0001";
        } else {
            int count = SERIAL_NUMBER.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat("0000");
            id = formatter.format(date)
                    + df.format(1 + Integer.parseInt(sno.substring(8, 12)));
        }
        return id;
    }
}
