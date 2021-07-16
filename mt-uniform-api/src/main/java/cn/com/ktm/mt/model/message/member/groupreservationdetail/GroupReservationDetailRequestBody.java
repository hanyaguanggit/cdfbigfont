package cn.com.ktm.mt.model.message.member.groupreservationdetail;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.Body;
import cn.com.ktm.mt.model.util.utils.ValidUtil;
import lombok.Data;

@Data
public class GroupReservationDetailRequestBody implements Body {

    private String userId;//系统商⽤户Id
    private String orgCode;//组织机构代码
    private String appointmentId;//预约单Id

    @Override
    public void valid() {
       /* Assert.notBlank(userId, ResponseConsts.ORDER_CONTACK_PARAM_ERROR,"系统商⽤户Id为空");
        Assert.notBlank(orgCode, ResponseConsts.ORDER_CONTACK_PARAM_ERROR,"组织机构代码为空");
        Assert.notBlank(appointmentId, ResponseConsts.ORDER_CONTACK_PARAM_ERROR,"预约单Id为空");
        Assert.isTrue(ValidUtil.uniformSocialCreditCodeValidate(orgCode), ResponseConsts.ORDER_GROUP_ORGCODE_ERROR, "组织机构代码输入有误");*/
    }
}
