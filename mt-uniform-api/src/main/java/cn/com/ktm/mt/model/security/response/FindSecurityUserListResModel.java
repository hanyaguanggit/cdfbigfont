package cn.com.ktm.mt.model.security.response;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * hyg
 * 条件查询系统用户列表响应体
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindSecurityUserListResModel implements Body {
    private  int userId;//用户id
    private  String userName;//姓名
    private  String loginName;//登录名
    private  String mobilePhoneNo;//手机号
    private  String email;//邮箱
    private Date expiryDate;//失效日期
    private Date createDate;//创建时间
    private boolean enabled;//启用/禁用
    private boolean locked;//锁定/未锁定
    private  int roleId;//角色id
    private  String roleName;//角色名称



    @Override
    public void valid() {
       // Assert.isTrue(pageIndex>=1, ResponseConsts.ORDER_PAGE_PARAM_ERROR,"页码最小值为1，请检查参数");
        //Assert.isTrue(pageSize>=1,ResponseConsts.ORDER_PAGE_PARAM_ERROR,"每⻚数量最小值为1，请检查参数");
       /* Assert.egt(pageIndex,1,ResponseConsts.LEVEL_MAPPING_PAGE_INDEX_ERROR);
        if(pageSize == null || pageSize == 0 ){
            pageSize = 500;
        }*/
    }
}
