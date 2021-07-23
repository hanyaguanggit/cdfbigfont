package cn.com.ktm.mt.model.security.request.user;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hyg
 * 条件查询系统用户列表入参
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindSecurityUserListReqBody implements Body {
    private  String searchKey;//搜索标志
    private  String searchContent;//搜索内容
    private  Integer pageIndex;//第几页
    private  Integer pageSize;//每页数量


    @Override
    public void valid() {
        Assert.isTrue(pageIndex>=1, ResponseConsts.ORDER_PAGE_PARAM_ERROR,"页码最小值为1，请检查参数");
        Assert.isTrue(pageSize>=1,ResponseConsts.ORDER_PAGE_PARAM_ERROR,"每⻚数量最小值为1，请检查参数");
    }
}
