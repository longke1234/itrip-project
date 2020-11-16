package com.lk.condition;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 用户注册条件类
 * @date : 2020-11-12 14:09
 */
@Data
@Api(value = "用户注册条件类")
public class UserRegisterCondition implements Serializable {

    @ApiModelProperty("邮箱或手机号")
    private String userCode;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("密码")
    private String userPassword;

    private static final long serialVersionUID = 5195499398400937348L;
}