package cn.com.ktm.mt.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsSecurityRoleMenu implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer menuid;

}