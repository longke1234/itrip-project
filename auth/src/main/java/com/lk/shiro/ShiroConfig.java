package com.lk.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : Shrio配置类
 * @date : 2020-11-18 21:20
 */
@Configuration
public class ShiroConfig {
    /**
     * 配置统一的安全管理器
     * @param myRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultSecurityManager getDefaultSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将安全管理器和自定义处理器完成绑定
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    /**
     * 将自定义的认证处理器纳入Spring 管理
     * @return
     */
    @Bean(name="myRealm")
    public MyRealm getMyRealm() {
        return new MyRealm();
    }

    /**
     * 配置自定义过滤器及路径过滤规则
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //指定安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //指定未验证的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/401");
        //指定不要过滤的路径
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        //登录请求不用通过自定义过滤器 anon表示不需要验证
        filterRuleMap.put("/api/dologin", "anon");
        filterRuleMap.put("/swagger-ui.html","anon");
        filterRuleMap.put("/swagger-ui.html/**","anon");
        filterRuleMap.put("/swagger-resources/**","anon");
        filterRuleMap.put("/webjars/**","anon");
        filterRuleMap.put("/v2/**", "anon");
        // 所有的请求通过我们自己的JWT filter
        filterRuleMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 将安全管理器整合到项目中
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}