package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelInventoryDayEntity implements Serializable {
    private static final long serialVersionUID = 7833795360027520675L; 
    private Integer limitDate;
    private Long    channelId;
    private Long    venueId;
    private Integer type;
    private Integer dayCount;
    private Integer inCount;
    private Long    id;
    private Long    createUser;
    private Long    createTime;
    private Long    updateUser;
    private Long    updateTime;
    private Long    ip;
    private Integer status;
    private Integer isDelete;
    private Integer orderPeriodType;

    private String remark;
    private String ticketIds;


}
