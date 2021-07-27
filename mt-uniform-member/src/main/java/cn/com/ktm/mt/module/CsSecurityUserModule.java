package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.bean.CsSecurityUser;
import cn.com.ktm.mt.model.bean.CsSecurityUserRole;
import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.OtaResponse;
import cn.com.ktm.mt.model.message.member.resetpassword.request.ResetPasswordRequest;
import cn.com.ktm.mt.model.message.member.resetpassword.response.ResetPasswordResponse;
import cn.com.ktm.mt.model.message.member.resetpassword.response.ResetPasswordResponseBody;
import cn.com.ktm.mt.model.redis.RedisCache;
import cn.com.ktm.mt.model.security.request.LoginRequestVo;
import cn.com.ktm.mt.model.security.request.user.EnabledReqVo;
import cn.com.ktm.mt.model.security.request.user.FindSecurityUserListReq;
import cn.com.ktm.mt.model.security.request.user.KeepSysUserReqVo;
import cn.com.ktm.mt.model.security.request.user.LockedReqVo;
import cn.com.ktm.mt.model.security.response.FindSecurityUserListResModel;
import cn.com.ktm.mt.model.security.response.LoginResponse;
import cn.com.ktm.mt.model.security.response.LoginResponseBody;
import cn.com.ktm.mt.model.util.utils.DateUtil;
import cn.com.ktm.mt.model.util.utils.ValidUtil;
import cn.com.ktm.mt.model.util.utils.security.AesUtils;
import cn.com.ktm.mt.service.CsSecurityUserRoleService;
import cn.com.ktm.mt.service.CsSecurityUserService;
import cn.com.ktm.mt.service.SecurityMenuService;
import com.github.pagehelper.PageHelper;
import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * hyg 2021.07.16
 * 后台管理员相关
 */
@Component
public class CsSecurityUserModule {
    Logger logger = LoggerFactory.getLogger(CsSecurityUserModule.class);

    @Autowired
    private CsSecurityUserService csSecurityUserService;

    @Autowired
    private SecurityMenuService securityMenuService;

    @Autowired
    private CsSecurityUserRoleService csSecurityUserRoleService;


    /**
     * 后台登录
     * @param request
     * @return
     */
    public LoginResponse adminLogin(LoginRequestVo request) {
        request.getBody().valid();
        if (!request.getBody().getValidCode().equalsIgnoreCase(RedisCache.db().get(request.getBody().getValidCode()))) {
           // Assert.fail(ResponseConsts.MEMBER_VALIDCODE_ERROR, "图形验证码校验失败");
        }
        //RedisCache.db().del(request.getBody().getValidCode());

        //先通过用户名和密码查询到用户id，再查询其拥有的菜单权限。
        LoginResponse response = new LoginResponse();
        try {
            String pass = AesUtils.encrypt("",request.getBody().getPassword());
            logger.info("解密的字符串:{}",pass);
            CsSecurityUser csSecurityUser = csSecurityUserService.selectByLoginNameAndPassword(request.getBody().getUserName(),pass);
            if(csSecurityUser != null){
                //TODO：1/是否验证后台用户失效日期;2/用户锁定后如何处理
                if(csSecurityUser.getLocked()){
                    response.setDescribe("此用户已被锁定，请联系管理员。");
                }
                //登录成功
                response.setCsSecurityUser(csSecurityUser);
                //获取权限菜单
                List<LoginResponseBody> menuList = securityMenuService.findMenuByUserId(csSecurityUser.getId());
                if(menuList != null && menuList.size() > 0){
                    logger.info("所拥有的菜单:{}",menuList);
                    response.setMenuList(menuList);
                    response.setCode(ResponseConsts.SUCCESS);
                    response.setDescribe("登录成功。");
                }else{
                    response.setCode(ResponseConsts.SUCCESS);
                    response.setDescribe("此人无任何权限！");
                }
            }else{
                response.setCode(ResponseConsts.SUCCESS);
                response.setDescribe("查无此人!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        }
        return response;
    }


    /**
     * hyg
     * 后台-修改密码
     * @param request
     * @return
     */
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
        request.getBody().valid();
        //Assert.isTrue(ValidUtil.isRightPwd(request.getBody().getOldPassword()),ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"当前密码必须由6至11位特殊字符、数字、大写字母和小写字母组成");
        Assert.isTrue(ValidUtil.isRightPwd(request.getBody().getNewPassword()),ResponseConsts.MEMBER_PARAM_CONTACT_ERROR,"新密码必须由6至11位特殊字符、数字、大写字母和小写字母组成");
        ResetPasswordResponse response = new ResetPasswordResponse();
        ResetPasswordResponseBody responseBody = new ResetPasswordResponseBody();
        String oldpass="",newpass="" ;
        try {
            oldpass = AesUtils.encrypt("", request.getBody().getOldPassword());
            newpass = AesUtils.encrypt("", request.getBody().getNewPassword());
            logger.info("旧密码加密后的字符串:{}",oldpass);
            logger.info("新密码加密后的字符串:{}",newpass);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.ERROR,"加密异常。");
        }
        CsSecurityUser csSecurityUser = csSecurityUserService.selectByUserIdAndPassword(request.getBody().getUserId(), oldpass);
        Assert.notNull(csSecurityUser, ResponseConsts.MEMBER_USERINFO_NOT_EXIST, "⽆效⽤户");
        csSecurityUser.setPassword(newpass);
        csSecurityUser.setLastModifiedUser(request.getBody().getCreateUserId());
        csSecurityUser.setLastModifiedTime(new Date());
        if(csSecurityUserService.updateByPrimaryKeySelective(csSecurityUser) > 0){
            responseBody.setUserId(csSecurityUser.getId());
            response.setBody(responseBody);
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("修改密码成功。");
        }else{
            response.setCode(ResponseConsts.ERROR);
            response.setDescribe("修改密码失败,请联系管理员。");
        }
        return response;
    }

    /**
     * hyg
     * 条件查询系统用户列表
     * @param request
     * @return
     */
    public OtaResponse selectUserListByCondition(FindSecurityUserListReq request){
        request.getBody().valid();
        OtaResponse response = new OtaResponse<>();
        List<FindSecurityUserListResModel> ulist = new ArrayList<>();
        PageHelper.startPage(request.getBody().getPageIndex(),request.getBody().getPageSize());
        try{
            List<FindSecurityUserListResModel> userListResBodyList = csSecurityUserService.selectUserListByCondition(request.getBody().getSearchKey(),request.getBody().getSearchContent());
            if(userListResBodyList.size() > 0){
                ulist = addrolename(userListResBodyList);
            }
            response.setBody(ulist.size() > 0 ? ulist :Collections.EMPTY_LIST);
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("查询成功。");
        }catch (Exception e ){
            e.printStackTrace();
            logger.info("查询系统用户异常，参数请求体：{}",request.getBody());
            response.setCode(ResponseConsts.ERROR);
            response.setDescribe("查询异常，请查看日志");
        }
        return response;
    }



    /**
     * hyg
     * 保存系统用户
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public OtaResponse keepSysUser(KeepSysUserReqVo request) {
        request.getBody().valid();
        OtaResponse response = new OtaResponse();
        CsSecurityUser csSecurityUser;
        if (request.getBody().getOperateType() == 0) {//添加
            csSecurityUser = addorupdate(request,0,new CsSecurityUser());
            int insertUser = csSecurityUserService.insertUser(csSecurityUser);
            Assert.isTrue(insertUser > 0, ResponseConsts.CREATE_SYS_USER_ERROR, "创建系统用户失败，id为空。");
            if (csSecurityUser.getId() == null) {
                response.setCode(ResponseConsts.ERROR);
                response.setDescribe("添加用户失败，请查看日志");
                return response;
            }
            //添加用户角色
            if (!"".equals(request.getBody().getRoleIds())) {
                List<Integer> rolelist = convertRole(request.getBody().getRoleIds());
                if (rolelist.size() > 0) {
                    rolelist.forEach(a -> {
                        CsSecurityUserRole csSecurityUserRole = new CsSecurityUserRole();
                        csSecurityUserRole.setUserid(csSecurityUser.getId());
                        csSecurityUserRole.setRoleid(a);
                        int bindrole = csSecurityUserRoleService.bindSecurityUserRole(csSecurityUserRole);
                        Assert.isTrue(bindrole > 0, ResponseConsts.CREATE_USER_ROLE_ERROR, "添加用户角色失败。");
                    });
                }
            }
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("添加成功");
        } else {
            //更新
            if (request.getBody().getUserId() != null) {
                csSecurityUser = csSecurityUserService.selectUserById(request.getBody().getUserId());
                Assert.isTrue(csSecurityUser != null, ResponseConsts.SYS_USER_NOT_EXSIT, "该用户不存在。");
                CsSecurityUser su = addorupdate(request,1,csSecurityUser);
                csSecurityUserService.updateByPrimaryKeySelective(su);
               // Assert.isTrue(updateUser > 0, ResponseConsts.UPDATE_SYS_USER_ERROR, "更新系统用户失败。");//在没有任何变更的时候进行保存，返回也是0。
                //更新用户角色
                //查询，比较，解绑，更新。
                if (!"".equals(request.getBody().getRoleIds())) {
                    List<CsSecurityUserRole> urlist = csSecurityUserRoleService.selectUseRoleByUserId(request.getBody().getUserId());
                    List<Integer> rolelist = convertRole(request.getBody().getRoleIds());
                    //如果所属角色没有变动，不做处理
                    boolean issame = urlist.stream().map(a -> a.getRoleid()).collect(Collectors.toSet())
                            .equals(rolelist.stream().collect(Collectors.toSet()));
                    if (!issame) {
                        if (urlist.size() > 0) {
                            urlist.forEach(ur -> {
                                int unbind = csSecurityUserRoleService.unbindUserRole(ur.getId());
                                Assert.isTrue(unbind > 0, ResponseConsts.DELETE_USER_ROLR_ERROR, "更新用户模块中，更新用户角色出现错误。");
                            });
                        }
                        if(rolelist.size() > 0){
                            for (int i = 0; i < rolelist.size(); i++) {
                                CsSecurityUserRole csSecurityUserRole = new CsSecurityUserRole();
                                csSecurityUserRole.setUserid(csSecurityUser.getId());
                                csSecurityUserRole.setRoleid(rolelist.get(i));
                                int bindrole = csSecurityUserRoleService.bindSecurityUserRole(csSecurityUserRole);
                                Assert.isTrue(bindrole > 0, ResponseConsts.CREATE_USER_ROLE_ERROR, "添加用户角色失败。");
                            }
                        }
                    }
                }
            } else {
                response.setCode(ResponseConsts.ERROR);
                response.setDescribe("更新用户不存在。");
            }
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("更新成功");
        }
        return response;
    }


    /**
     * hyg
     * 锁定/未锁定
     * @param request
     * @return
     */
    public OtaResponse updateLockedUser(LockedReqVo request){
        request.getBody().valid();
        OtaResponse response = new OtaResponse();
        if(request.getBody().getUserId() != null && request.getBody().getUserId() != 0){
            CsSecurityUser csSecurityUser = csSecurityUserService.selectUserById(request.getBody().getUserId());
            if(csSecurityUser != null){
                int update = csSecurityUserService.updateUserLockById(request.getBody().getLocked(),request.getBody().getUserId());
                Assert.isTrue(update > 0 ,ResponseConsts.UPDATE_SYS_USER_LOCKED_ERROR,"更新锁定状态失败。");
                response.setCode(ResponseConsts.SUCCESS);
                response.setDescribe("更新成功。");
            }else{
                response.setCode(ResponseConsts.ERROR);
                response.setDescribe("更新失败，查无此人");
            }
        }
        return response;
    }

    /**
     * hyg
     * 启用/未启用
     * @param request
     * @return
     */
    public OtaResponse updateEnabledUser(EnabledReqVo request){
        request.getBody().valid();
        OtaResponse response = new OtaResponse();
        CsSecurityUser csSecurityUser = csSecurityUserService.selectUserById(request.getBody().getUserId());
        if(csSecurityUser != null){
            int update = csSecurityUserService.updateUserEnabledById(request.getBody().getEnabled(),request.getBody().getUserId());
            Assert.isTrue(update > 0 ,ResponseConsts.UPDATE_SYS_USER_ENABLED_ERROR,"更新禁用状态失败。");
            response.setCode(ResponseConsts.SUCCESS);
            response.setDescribe("更新成功。");
        }else{
            response.setCode(ResponseConsts.ERROR);
            response.setDescribe("更新失败,查无此人");
        }
        return response;
    }

    /**
     * rolestr转换
     * @param rolestr
     * @return
     */
    public List<Integer> convertRole(String rolestr){
        List<Integer> rolelist = new ArrayList<>();
        String[] roleid = rolestr.split(",");
        for (int i = 0; i < roleid.length; i++) {
            rolelist.add(Integer.parseInt(roleid[i].trim()));
        }
        return rolelist;
    }

    /**
     * hyg
     * 根据操作类型进行封装
     * @param request
     * @param ope
     * @return
     */
    public CsSecurityUser addorupdate(KeepSysUserReqVo request,Integer ope,CsSecurityUser csSecurityUser){
        if (ope == 0) {
            String passStr = "";
            try {
                passStr = AesUtils.encrypt("", request.getBody().getPassword());
            } catch (Exception e) {
                logger.info("密码加密出错，密码：{}", request.getBody().getPassword());
                e.printStackTrace();
            }
            Date expiryDate = null;
            if (!"".equals(request.getBody().getExpiryDate())) {
                try {
                    expiryDate = DateUtil.parse(request.getBody().getExpiryDate(), DateUtil.format3);
                } catch (ParseException e) {
                    e.printStackTrace();
                    logger.info("日期转换异常，转换对象：{}", request.getBody().getExpiryDate());
                }
            }
            csSecurityUser.setUserName(request.getBody().getUserName());
            csSecurityUser.setPassword(passStr);
            csSecurityUser.setExpiryDate(expiryDate);
            csSecurityUser.setLocked(false);//是否锁定，默认否
            csSecurityUser.setCredentialExpiryDate(expiryDate);//暂定于失效日期一致
            csSecurityUser.setEnabled(request.getBody().getEnabled() == 1 ? true : false);//是否启用，1：启用 0：禁用
            csSecurityUser.setAvatar(request.getBody().getAvatar());
            csSecurityUser.setLoginName(request.getBody().getLoginName());
            csSecurityUser.setMobilePhoneNo(request.getBody().getMobilePhoneNo());
            csSecurityUser.setEmail(request.getBody().getEmail());
            csSecurityUser.setCreateDate(new Date());
            csSecurityUser.setDeleted((short) 0); //删除状态 0：未删除  1：删除
            csSecurityUser.setCreator(request.getBody().getCreator());
            csSecurityUser.setLastModifiedUser(request.getBody().getCreator());
            csSecurityUser.setLastModifiedTime(new Date());
            csSecurityUser.setShopId(request.getBody().getShopId());
        } else {
            csSecurityUser.setUserName(request.getBody().getUserName());
            csSecurityUser.setAvatar(request.getBody().getAvatar());
            csSecurityUser.setMobilePhoneNo(request.getBody().getMobilePhoneNo());
            csSecurityUser.setEmail(request.getBody().getEmail());
            csSecurityUser.setEnabled(request.getBody().getEnabled() == 1 ? true : false);
            Date expiryDate = null;
            if (!"".equals(request.getBody().getExpiryDate())) {
                try {
                    expiryDate = DateUtil.parse(request.getBody().getExpiryDate(), DateUtil.format3);
                } catch (ParseException e) {
                    e.printStackTrace();
                    logger.info("失效日期转换异常，转换对象：{}", request.getBody().getExpiryDate());
                }
            }
           // csSecurityUser.setId(request.getBody().getUserId());
            csSecurityUser.setExpiryDate(expiryDate);
            csSecurityUser.setLastModifiedUser(request.getBody().getCreator());
            csSecurityUser.setLastModifiedTime(new Date());
           // csSecurityUser.setShopId(request.getBody().getShopId());
        }
        return csSecurityUser;
    }

    /**
     * hyg
     * 一个人多个角色，把角色名称合并
     * @param list
     * @return
     */
    public List<FindSecurityUserListResModel> addrolename(List<FindSecurityUserListResModel> list){
        List<FindSecurityUserListResModel> newlist = new ArrayList<>();
        //根据用户id分组
        Map<Integer, List<FindSecurityUserListResModel>> sameuser = list.stream().collect(Collectors.groupingBy(FindSecurityUserListResModel::getUserId));
        for (Integer i: sameuser.keySet()) {
            StringBuilder roleStr = new StringBuilder();
            for (FindSecurityUserListResModel u:sameuser.get(i)) {
                if(u.getRoleName() != null && !"".equals(u.getRoleName())){
                    roleStr.append(u.getRoleName()).append(",");
                }
            }
            FindSecurityUserListResModel ur =  sameuser.get(i).get(0);
            if(roleStr.length() >= 1){
                ur.setRoleName(roleStr.substring(0,roleStr.length()-1));//去掉末尾逗号
            }
            logger.info("系统用户信息：{}",ur);
            newlist.add(ur);
        }

        return newlist;
    }
}
