package com.lk.seriver.impl;

import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.exception.ServiceException;
import com.lk.seriver.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 邮件服务实现类
 * @date : 2020-11-12 19:38
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    /**
     * Spring-boot发送邮件接口
     */
    @Resource
    private MailSender mailSender;

    @Override
    public void sendActivationMail(String maiTo, String activationCode) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //设置收件人的邮箱地址
            simpleMailMessage.setTo(maiTo);
            //指定需要发送的的内容
            simpleMailMessage.setText("你的激活码是："+activationCode);
//            simpleMailMessage.setText("你可真是个蠢货！");
            //主题
            simpleMailMessage.setSubject("偷偷的打开..........");
            //发送邮件
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            log.warn(e.getMessage(),e);
            throw new ServiceException(ErrorCodeEnum.ERROR_CALLING_THIRD_PARTY_SERVICE);
        }
    }
}