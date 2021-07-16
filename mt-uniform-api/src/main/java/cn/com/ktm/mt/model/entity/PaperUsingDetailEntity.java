package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperUsingDetailEntity {

    private Long id;
    private Long batchId;
    private Long invoiceNo;
    private Long deviceId;
    private Integer usedPaperEndNo;
    private Integer usedNum;
    private Long createUser;
    private Long createTime;
    private Integer isDelete;
}
