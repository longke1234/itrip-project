package com.lk.seriver.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.User;
import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.constants.SystemConstants;
import com.lk.common.exception.ServiceException;
import com.lk.condition.UserRegisterCondition;
import com.lk.mapper.UserMapper;
import com.lk.seriver.MailService;
import com.lk.seriver.SmsService;
import com.lk.seriver.UserService;
import com.lk.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;


import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /*用户注册验证吗的前缀*/
    final String activeCodeKeyPre = "active:";

    /*是否发送邮件的开关*/
    @Value(value = "${email.send.enable}")
    private boolean enableSendEmail;

    /*是否发送短信的开关*/
    @Value(value = "${sms.send.enable}")
    private boolean enableSeanSms;

    @Resource
    private MailService mailService;

    @Resource
    private SmsService smsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean userRegister(UserRegisterCondition condition,String registerType) {
            //根据用户名进行第二次，是否存在
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new QueryWrapper<User>().lambda();
            userLambdaQueryWrapper.eq(User::getUserCode,condition.getUserCode());
            User user = this.getOne(userLambdaQueryWrapper);
            if (!StringUtils.isEmpty(user)){
                log.warn("账号已存在");
                //用户抛出异常,用户已存在
                throw new ServiceException(ErrorCodeEnum.AUTH_USER_ALREADY_EXISTS);
            }
            //密码加密
            condition.setUserPassword(MD5.getMd5(condition.getUserPassword(),32));
            User user1 = new User();
            BeanUtils.copyProperties(condition,user1);
            // 初始化其他数据,指定用户类型(默认为自注册用户)
            user1.setUserType(SystemConstants.REGISTRATION);
            //将用户数据插入数据库
            boolean save = this.save(user1);
            if (!save) {
                throw new ServiceException(ErrorCodeEnum.FAILED);
            }
            //根据不同的注册类型进行不同生成验证规则
            switch (registerType){
                case "email":
                    //生成激活码， 通过当前系统时间缀进行 32 位 MD5 加密，
                    String activationCode = MD5.getMd5(String.valueOf(System.currentTimeMillis()),32);
                    log.info("激活码: {}",activationCode);
                    //发送邮件,
                    // 可以通过定义一个发送邮件的开关，根据不同的环境来确定是否需要发送，
                    // 譬如开发环境和测试环境就可以不用发送，只需要在生产环境
                    if(enableSendEmail){
                        mailService.sendActivationMail(user1.getUserCode(),activationCode);
                    }
                    //激活码存入redis中，过期时间为30分钟
                    stringRedisTemplate.opsForValue().set(activeCodeKeyPre+user1.getUserCode(),activationCode,30, TimeUnit.MINUTES);
                    break;
                case "phone":
                        //生成验证码
                        int randomCode = MD5.getRandomCode();
                        log.info("验证码:{}",randomCode);
                        if (enableSeanSms){
                            //发送验证码
                            smsService.sendMsg(user1.getUserCode(),String.valueOf(randomCode));
                        }
                        // 将短信验证码存入redis中，过期时间设为5分分钟
                        stringRedisTemplate.opsForValue().set(activeCodeKeyPre+user1.getUserCode(),String.valueOf(randomCode),5,TimeUnit.MINUTES);
                    break;

                default:
                        break;
            }
        return true;
    }

    @Override
    public boolean vailDatePhone(String phoneNum, String code) {
        //对比前端填写的验证码与发送的验证码
        String key = activeCodeKeyPre+phoneNum;
        //从redis中取出验证码
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value != null && value.equals(code)) {
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new QueryWrapper<User>().lambda();
            userLambdaQueryWrapper.eq(User::getUserCode,phoneNum);
            User user = this.getOne(userLambdaQueryWrapper);
            if (!StringUtils.isEmpty(user)) {
                //更新用户为激活状态
                user.setActivated(SystemConstants.IS_ACTIVE);
                //品台ID 注册ID
                user.setFlatId(user.getId());
                //用户类型 自注册用户
                user.setUserType(SystemConstants.REGISTRATION);
                this.updateById(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public User findUserByUserCode(String userCode) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new QueryWrapper<User>().lambda();
        userLambdaQueryWrapper.eq(User::getUserCode,userCode);
        return this.getOne(userLambdaQueryWrapper);
    }
}
