package com.lk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lk.bean.User;
import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.vo.ReturnResult;
import com.lk.condition.UserRegisterCondition;
import com.lk.seriver.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


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
@Api(tags = "用户相关控制器")
@RequestMapping(value = "/api")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 校验当前用户是否存在
     * @param name
     * @return
     */
    @ApiOperation(value = "检查用户名是否已存在",httpMethod = "GET",protocols = "HTTP",produces = MediaType.APPLICATION_JSON_VALUE,response = ReturnResult.class,notes = "验证是否已存在该用户名")
    @RequestMapping(value = "ckusr",method = RequestMethod.GET)
    public ReturnResult checkUser(@ApiParam(name = "name",value = "待检验用户名") @RequestParam String name){
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

    /**
     * 邮箱验证
     * @param condition
     * @return
     */
    @ApiOperation(value = "邮箱验证",httpMethod = "POST",protocols = "HTTP",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/doregister",produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnResult doRegister(@RequestBody @ApiParam(name = "UserRegisterCondition",value = "用户注册条件类") UserRegisterCondition condition) {
        //校验邮箱格式是否符合要求
        if (!validEmail(condition.getUserCode())) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_ILLEGAL_USERCODE);
        }
        boolean result = userService.userRegister(condition,"email");
        if (result) {
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }

    /**
     * 通过手机号验证
     * @return
     */
    @ApiOperation(value = "手机号验证",protocols = "HTTP",produces = MediaType.APPLICATION_JSON_VALUE,httpMethod = "POST")
    @PostMapping(value = "doRegisterByPhone")
    public ReturnResult doRegisterByPhone(@RequestBody @ApiParam(name = "UserRegisterCondition",value = "用户注册条件类") UserRegisterCondition condition){
        //手机格式验证
        if (!this.validPhone(condition.getUserCode())) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_ILLEGAL_USERCODE);
        }
        boolean result = userService.userRegister(condition, "phone");
        if (result) {
            return ReturnResult.ok();
        }
        return ReturnResult.error(ErrorCodeEnum.AUTH_ACTIVATE_FAILED);
    }


    /**
     * 激活用户
     * @param phone 用户名
     * @param code 验证码
     * @return
     */
    @ApiOperation(value = "激活用户",httpMethod = "PUT",produces = MediaType.APPLICATION_JSON_VALUE,protocols = "HTTP")
    @RequestMapping(value = "/activateByPhone",method = RequestMethod.PUT)
    public ReturnResult activateByPhone(@RequestParam("user")@ApiParam(name = "user",value = "用户名") String phone,
                                        @RequestParam("code")@ApiParam(name = "code",value = "验证码") String code){
        boolean result = userService.vailDatePhone(phone, code);
        if (result) {
            return ReturnResult.ok();
        }
        return ReturnResult.error( );
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

    /**
     * 验证手机号码的格式是否正确
     * @param phone 手机号码
     * @return 返回true表示手机号码验证通过。否则返回false
     */
    public boolean validPhone(String phone) {
        String regex = "^1[3|4|5|7|8][0-9]{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }

}