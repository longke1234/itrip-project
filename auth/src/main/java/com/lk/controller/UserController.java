package com.lk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lk.bean.User;
import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.vo.ReturnResult;
import com.lk.condition.UserRegisterCondition;
import com.lk.seriver.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 用户控制器
 * @date : 2020-11-10 13:54
 */
@RestController
@Api(value = "用户相关控制器")
@RequestMapping(value = "/api")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 校验当前用户是否存在
     * @param name
     * @return
     */
    @RequestMapping(value = "ckusr")
    public ReturnResult checkUser(String name){
        //校验用户名是否为空
        if (StringUtils.isEmpty(name)){
            return ReturnResult.error(ErrorCodeEnum.AUTH_USER_ALREADY_EXISTS);
        }
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new QueryWrapper<User>().lambda();
        userLambdaQueryWrapper.eq(User::getUserCode,name);
        User user = userService.getOne(userLambdaQueryWrapper);
        if (user != null){
            //当前用户存在，则校验不通过
            return ReturnResult.error(ErrorCodeEnum.AUTH_USER_ALREADY_EXISTS);
        }
        return ReturnResult.ok();
    }

    @PostMapping(value = "/doregister",produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnResult doRegister(@RequestBody UserRegisterCondition condition) {
        //校验邮箱地址是否符合要求
        if (!validEmail(condition.getUserCode())) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_ILLEGAL_USERCODE);
        }
        boolean result = userService.userRegister(condition);
        if (result) {
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }

    /**
     * 通过正则表达式校验邮箱地址是否符合要求
     * 合法E-mail地址：
     * 1. 必须包含一个并且只有一个符号“@”
     * 2. 第一个字符不得是“@”或者“.”
     * 3. 不允许出现“@.”或者.@
     * 4. 结尾不得是字符“@”或者“.”
     * 5. 允许“@”前的字符中出现“＋”
     * 6. 不允许“＋”在最前面，或者“＋@”
     * @param email
     * @return
     */
    private boolean validEmail(String email){
        String regex="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return Pattern.compile(regex).matcher(email).find();
    }

}