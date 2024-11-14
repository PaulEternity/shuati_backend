package com.paul.project.satoken;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.paul.project.model.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.paul.project.constant.UserConstant.USER_LOGIN_STATE;

public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId,String loginType){
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object loginId,String loginType){
        User user = (User) StpUtil.getSessionByLoginId(loginId).get(USER_LOGIN_STATE);
        return Collections.singletonList(user.getUserRole());
    }
}
