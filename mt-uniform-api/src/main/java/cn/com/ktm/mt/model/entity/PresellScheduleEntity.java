package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 网上预售门票排期开闭馆
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresellScheduleEntity implements Serializable {
	private static final long serialVersionUID = -5809782578272943999L;

	private Integer onoffDate;
	private Integer onoff;//1开, 0闭
	private Integer isAppointment;//1可预约，0不可预约
	private String  remark;
	private Integer seasonType; //淡旺季类型：1淡季，2旺季
	private Long    id;
	private Long    createUser;
	private Long    createTime;
	private Long    updateUser;
	private Long    updateTime;
	private String  ip;
	private Integer status;//1可用（默认）,0不可用
	private Integer isDelete;//1已删除, 0未删除（默认）
	private Integer storeCount; //剩余库存数
}
