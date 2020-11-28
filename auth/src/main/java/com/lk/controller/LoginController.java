package com.lk.controller;

import com.lk.bean.User;
import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.vo.ReturnResult;
import com.lk.common.vo.TokenVo;
import com.lk.seriver.UserService;
import com.lk.shiro.JwtUtil;
import com.lk.util.JacksonUtil;
import com.lk.util.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 登录控制器
 * @date : 2020-11-18 21:57
 */
@Api(tags = "登录相关控制器")
@RestController
@RequestMapping(value = "/api")
@Slf4j
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(
            value = "用户登录接口",
            //响应类型
            response = ReturnResult.class,
            //请求类型
            httpMethod = "POST",
            //返回数据类型
            produces = MediaType.APPLICATION_JSON_VALUE,
            //协议
            protocols = "HTTP")
    @RequestMapping(value = "/dologin",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnResult login(
            @ApiParam(name = "name",value = "用户名")
            @RequestParam("name")String userCode,
            @ApiParam(name = "password",value = "登录密码")
            @RequestParam("password")String password){
        //验证参数的准确性
        if (StringUtils.isEmpty(userCode)||StringUtils.isEmpty(password)) {
            //返回错误：参数为空
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        //根据用户名查询
        User user = userService.findUserByUserCode(userCode);
        if (user == null) {
            //返回错误：用户或密码错误
            return ReturnResult.error(ErrorCodeEnum.AUTH_AUTHENTICATION_FAILED);
        }
        //查询密码是否真确
        if (!MD5.getMd5(password,32).equals(user.getUserPassword())) {
            //当密码不匹配时返回错误信息：用户或密码错误
            return ReturnResult.error(ErrorCodeEnum.AUTH_AUTHENTICATION_FAILED);
        }
        String jwtToken = null;
        try {
            //生成jwtToken
            jwtToken = JwtUtil.sign(userCode, MD5.getMd5(password,32));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
            e.printStackTrace();
        }
        //封装成tokenVo
        TokenVo tokenVo = new TokenVo(jwtToken,System.currentTimeMillis()+2*60*60*1000,System.currentTimeMillis());
        //将用户信息存储到redis中，以jwtToken做为key，跟需求有一些出入，不区分移动端和PC端
        stringRedisTemplate.opsForValue().set(jwtToken,JacksonUtil.objectToJsonString(user), 2, TimeUnit.HOURS);
        return ReturnResult.ok(tokenVo);
    }
}