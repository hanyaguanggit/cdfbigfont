package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperStoreEntity {

    private Long id;
    private Long totalNum;
    private Long applyNum;
    private Long createUser;
    private Long createTime;
    private Long updateUser;
    private Long updateTime;
    private Integer isDelete;
}
