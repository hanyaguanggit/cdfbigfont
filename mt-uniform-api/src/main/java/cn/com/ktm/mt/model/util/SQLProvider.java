package cn.com.ktm.mt.model.util;

import cn.com.ktm.mt.model.entity.RefundEntity;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class SQLProvider {
    public String getPartnerId(Integer channelId) {
        return "select partnerId from t_partner_channel where channelId =  " + channelId;
    }

    public String getAnnouncementList(Integer pageSize, Integer PageIndex) {
        return "select type announcementType, id noticeId, title,   SUBSTR(FROM_UNIXTIME( createTime/1000 ) ,1,19 ) date from t_announcement order by createTime limit  " + PageIndex + "," + pageSize;
    }

    public String getAnnouncementInfo(String noticeId) {
        return "select id noticeId , title,content,type announcementType,  SUBSTR(FROM_UNIXTIME( createTime/1000 ) ,1,19 ) date from t_announcement where id = " + noticeId;
    }

    public String getChannels(Integer pageIndex, Integer pageSize) {
        return "select partnerId,t_channel.id channelId, t_channel.status isValid,  SUBSTR(FROM_UNIXTIME(t_channel.createTime/1000 ) ,1,19 ) firstValidTime , SUBSTR(FROM_UNIXTIME( t_channel.updateTime/1000 ) ,1,19 ) lastUpdateTime  from t_channel left join t_partner_channel on t_channel.id =  t_partner_channel.channelId limit  " + pageIndex + "," + pageSize;
    }

    public String insertSelective(Map<String, Object> para) {
        RefundEntity blog = (RefundEntity) para.get("bean"); //

        SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL

        sql.INSERT_INTO("t_refund");

        if (blog.getOrderId() != null) { //判断blogId属性是否有值
            sql.VALUES("orderId", blog.getOrderId().toString());
        }

        if (blog.getOrderDetails() != null) {//判断title属性是否有值
            sql.VALUES("orderDetails", blog.getOrderDetails());
        }
        if (blog.getTradeOrderId() != null) {//判断title属性是否有值
            sql.VALUES("tradeOrderId", blog.getTradeOrderId());
        }
        if (blog.getTotal() != null) {//判断title属性是否有值
            sql.VALUES("total", blog.getTotal().toString());
        }
        if (blog.getRefundReason() != null) {//判断title属性是否有值
            sql.VALUES("refundReason", blog.getRefundReason());
        }
        if (blog.getAuditStatus() != null) {//判断title属性是否有值
            sql.VALUES("auditStatus", blog.getAuditStatus().toString());
        }
        if (blog.getReason() != null) {//判断title属性是否有值
            sql.VALUES("reason", blog.getReason());
        }

        if (blog.getCreateTime() != null) {//判断title属性是否有值
            sql.VALUES("createTime", blog.getCreateTime().toString());
        }

        if (blog.getRefundBatchNo() != null) {//判断title属性是否有值
            sql.VALUES("refundBatchNo", blog.getRefundBatchNo());
        }

        if (blog.getRefundType() != null) {//判断title属性是否有值
            sql.VALUES("refundType", blog.getRefundType().toString());
        }

        if (blog.getChannelId() != null) {//判断title属性是否有值
            sql.VALUES("channelId", blog.getChannelId().toString());
        }

        if (blog.getIsForce() != null) {//判断title属性是否有值
            sql.VALUES("isForce", blog.getIsForce().toString());
        }

        if (blog.getAuditUserType() != null) {//判断title属性是否有值
            sql.VALUES("auditUserType", blog.getAuditUserType().toString());
        }
        if (blog.getApplyUserId() != null) {//判断title属性是否有值
            sql.VALUES("applyUserId", blog.getApplyUserId().toString());
        }


        return sql.toString();
    }


    public static void main(String[] args) {

        System.out.println(new SQLProvider().getChannels(0, 10));
        System.out.println(new SQLProvider().getAnnouncementList(10, 0));
        System.out.println(new SQLProvider().getAnnouncementInfo("4"));

    }
}
