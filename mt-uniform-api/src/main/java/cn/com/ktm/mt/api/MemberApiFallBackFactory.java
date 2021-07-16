package cn.com.ktm.mt.api;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.entity.OrganizationEntity;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.announcement.QueryAnnouncementListRequest;
import cn.com.ktm.mt.model.message.member.announcement.info.QueryAnnoInfoRequest;
import cn.com.ktm.mt.model.message.member.groupLogin.request.GroupLoginRequest;
import cn.com.ktm.mt.model.message.member.sync.SyncMTGroupAduitBean;
import cn.com.ktm.mt.model.message.member.validcode.request.ValidCodeRequest;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MemberApiFallBackFactory implements FallbackFactory<MemberApi> {
    @Override
    public MemberApi create(Throwable throwable) {

        return new MemberApi() {
          /*  @Override
            public OtaResponse personalLogin(PersonalLoginRequest request) {
            	return OtaResponse.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION, "网络通讯中，请稍后....",request.getPartnerId(),request.getChannelId());
            }*/

          /*  @Override
            public OtaResponse groupRegister(GroupRegisterRequest request) {
            	return OtaResponse.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION, "网络通讯中，请稍后....",request.getPartnerId(),request.getChannelId());

            }*/

            @Override
            public OtaResponse groupLogin(GroupLoginRequest request) {
            	return OtaResponse.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION, "网络通讯中，请稍后....",request.getPartnerId(),request.getChannelId());

            }

            @Override
            public byte[] getValidCode(ValidCodeRequest request) {
                return new byte[0];
            }

          /*  @Override
            public OtaResponse queryUserInfo(UserInfoRequest request) {
            	return OtaResponse.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION, "网络通讯中，请稍后....",request.getPartnerId(),request.getChannelId());

            }
*/
            /*@Override
            public OtaResponse resetPassword(ResetPasswordRequest request) {
            	return OtaResponse.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION, "网络通讯中，请稍后....",request.getPartnerId(),request.getChannelId());

            }
*/
           /* @Override
            public OtaResponse sendSmsCode(SendSmsVeriﬁcationCodeRequest request) {
            	return OtaResponse.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION, "网络通讯中，请稍后....",request.getPartnerId(),request.getChannelId());

            }
*/

            @Override
            public OrganizationEntity findGroupInfo(String userId) {
            	return null;

            }

            @Override
            public OtaResponse syncMTGroupAduit(SyncMTGroupAduitBean request) {
            	return null;

            }

            @Override
            public OtaResponse queryAnnouncementList(QueryAnnouncementListRequest request) {
            	return OtaResponse.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION, "网络通讯中，请稍后....",request.getPartnerId(),request.getChannelId());

            }

            @Override
            public OtaResponse queryAnnouncementInfo(QueryAnnoInfoRequest request) {
            	return OtaResponse.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION, "网络通讯中，请稍后....",request.getPartnerId(),request.getChannelId());

            }


        };
    }
}
