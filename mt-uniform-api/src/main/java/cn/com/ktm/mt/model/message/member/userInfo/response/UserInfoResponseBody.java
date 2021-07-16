package cn.com.ktm.mt.model.message.member.userInfo.response;

import cn.com.ktm.mt.model.message.Body;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class UserInfoResponseBody implements Body {

    private Integer status;//状态(可⽤/不可⽤） 1可⽤ 0不可⽤ userType = 1 可不填。 userType = 2,3 必须填
    private String email;//邮箱 非必须
    private Integer auditStatus;//审核状态（通过/不通过/审核中） 0未审核，1已通过，2已拒绝 userType = 1 可不填。 userType = 2,3 必须填
    private String orgName;//机构名称 userType = 1 可不填。 userType = 2,3 必须填
    private String contactName;//联系⼈姓名 非必须
    private String phone;// ⼿机号
    private String orgCode ;// 组织机构代码 userType = 1 可不填。 userType = 2,3 必须填
    private Map<String, List<String>> submitAuditContent;// 提交的审核内容 userType = 1 可不填。 userType = 2,3 必须填 key,val形式 1. key为审核⽂件的类型 2. val为⽂件的url的数组，因为可 能为多个⽂件 key =  orgCodeFiles  组织机构代 码扫描件（营业执照） key =   licenseFiles 旅⾏业务许可 证扫描件（特殊团队选填） key = policyFiles  旅⾏责任保险保 单扫描件（特殊团队选填，多张） key =  subOrgFiles  旅⾏社分社备 案登记证明扫描件（特殊团队选 填，多张）




    @Override
    public void valid() {

    }
}
