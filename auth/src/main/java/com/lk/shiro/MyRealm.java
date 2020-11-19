package com.lk.shiro;

import com.lk.bean.User;
import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.exception.ServiceException;
import com.lk.seriver.UserService;
import com.lk.util.JacksonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.rmi.ServerException;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义认证与鉴权处理器
 * @date : 2020-11-18 11:17
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 判断当前传递过来的token类型是否为自定义的jwtToken
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 鉴权，授权
     * 此期没有涉及到用户的角色，所只不需要实现鉴权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * 默认使用此方法进行用户正确与否验证，错误抛出异常即可
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token中的信息
        String token = (String)authenticationToken.getCredentials();
        if (token==null) {
            //如果当前jwtToken为空，表示为传递过来
            //返回异常 token为空
            throw new ServiceException(ErrorCodeEnum.AUTH_TOKEN_IS_EMPTY);
        }
        //解密获取username,用于和数据库进行比较
        String username = JwtUtil.getUsername(token);
        if (username==null) {
            //返回异常 用户不存在
            throw new ServiceException(ErrorCodeEnum.AUTH_UNKNOWN);
        }
        //根据传递过来的username,也就是跟据userCode查询用户信息
        User user = userService.findUserByUserCode(username);
        if (user==null) {
            //返回异常 用户名或密码错误
            throw new ServiceException(ErrorCodeEnum.AUTH_AUTHENTICATION_FAILED);
        }
        if (!JwtUtil.verify(token, username, user.getUserPassword())) {
            //当用户名和密码验证不通过返回 用户名或密码错误
            throw new ServiceException(ErrorCodeEnum.AUTH_AUTHENTICATION_FAILED);
        }
        return new SimpleAuthenticationInfo(token,token,"my_realm");
    }
}