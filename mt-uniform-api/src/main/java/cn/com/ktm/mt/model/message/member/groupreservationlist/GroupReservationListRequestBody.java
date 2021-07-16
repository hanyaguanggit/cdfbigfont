package cn.com.ktm.mt.model.message.member.groupreservationlist;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import lombok.Data;

@Data
public class GroupReservationListRequestBody implements Body {
    private String userId;//系统商⽤户ID
    private Integer pageIndex;//第⼏⻚
    private Integer pageSize;//每⻚数量（默认每⻚20条）

    @Override
    public void valid() {
        /*Assert.notBlank(userId, ResponseConsts.ORDER_CONTACK_PARAM_ERROR,"系统商⽤户ID为空");
        Assert.notNull(pageIndex, ResponseConsts.ORDER_CONTACK_PARAM_ERROR,"页码为空");
        Assert.notNull(pageSize, ResponseConsts.ORDER_CONTACK_PARAM_ERROR,"每⻚数量为空");*/

        Assert.isTrue(pageIndex >= 1,ResponseConsts.ORDER_PAGE_PARAM_ERROR,"页码最小值为1，请检查参数");
        Assert.isTrue(pageSize >= 1,ResponseConsts.ORDER_PAGE_PARAM_ERROR,"每⻚数量最小值为1，请检查参数");

    }
}
