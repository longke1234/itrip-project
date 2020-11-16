package com.lk.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.bean.User;
import com.lk.condition.UserRegisterCondition;

public interface UserService extends IService<User>{
    /**
     *用户注册，邮箱注册
     * @return
     */
    boolean userRegister(UserRegisterCondition condition);

    /**
     * 用户注册，手机短信注册
     * @param user
     */
    void insertUserByPhone(User user);
}
