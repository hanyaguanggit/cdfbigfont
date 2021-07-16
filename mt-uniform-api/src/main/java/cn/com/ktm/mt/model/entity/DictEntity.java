package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictEntity implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    private String code;
    private String value;
    private Integer type;
    private String remark;
    private Long id;
    private Long createUser;
    private Long createTime;
    private Long updateUser;
    private Long updateTime;
    private String ip;
    private Integer status;//1可用（默认）,0不可用
    private Integer isDelete;//1已删除, 0未删除（默认）
}
