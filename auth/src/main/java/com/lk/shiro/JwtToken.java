package com.lk.shiro;

    import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义JwtToken
 * @date : 2020-11-18 11:10
 */
public class JwtToken implements AuthenticationToken, Serializable {

    private static final long serialVersionUID = 7996167247554303479L;

    //密钥
    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}