package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDayEntity implements Serializable {
	private static final long serialVersionUID = -5809782578272943999L;

	private Integer limitDate;
	private Long    venueId;
	private Integer type;
	private Integer dayCount;
	private Integer inCount;
	private Long    id;
	private Long    createUser;
	private Long    createTime;
	private Long    updateUser;
	private Long    updateTime;
	private String  ip;
	private Integer status;
	private Integer isDelete;
	private String  remark;
	private Integer orderPeriodType;
}
