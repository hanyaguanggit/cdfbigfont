package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperUsingEntity {

    private Long id;
    private Long invoiceNo;
    private Integer paperBeginNo;
    private Integer paperEndNo;
    private Long deviceId;
    private Integer applyNum;
    private Integer usedNum;
    private Long createUser;
    private Long createTime;
    private Long updateUser;
    private Long updateTime;
    private Integer isDelete;
}
