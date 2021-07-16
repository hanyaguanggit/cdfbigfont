package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionIncomeEntity implements Serializable {
	private static final long serialVersionUID = -5809782578272943999L;

	private Long id;
	private Long tradeTime;
	private Integer orderChannel;//1预售， 2现场
	private Integer payWay;//1现金，2支付宝，3银联
	private Integer orderQuantity;
	private Double payTotal;
	private Integer refundQuantity;
	private Double refundTotal;
	private Double netIncome;//净收入

	private Long createUser;
	private Long createTime;
	private Long updateUser;
	private Long updateTime;
	private String ip;
	private Integer status;//2已完成(验票)，1已创建（默认）,0已取消,-1已过期超时
	private Integer isDelete;//1已删除, 0未删除（默认）
}
