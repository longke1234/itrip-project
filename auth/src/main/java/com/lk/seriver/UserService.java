package com.lk.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.bean.User;
import com.lk.condition.UserRegisterCondition;

public interface UserService extends IService<User>{
    /**
     *用户注册，邮箱注册
     * @param registerType 注册类型 email or phone
     * @return
     */
    boolean userRegister(UserRegisterCondition condition, String registerType);

    /**
     *
     * @param phoneNum 手机号
     * @param code 验证码
     * @return true验证成功，false验证失败
     */
    boolean vailDatePhone(String phoneNum, String code);

    /**
     * 根据用户账号查询用户信息
     * @param userCode
     * @return
     */
    User findUserByUserCode(String userCode);
}
