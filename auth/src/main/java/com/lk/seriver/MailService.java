package com.lk.seriver;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 邮件服务接口
 * @date : 2020-11-12 19:33
 */
public interface MailService {

    /**
     *  发送包括激活码的邮件，用于激活用户账号
     * @param maiTo 收件人的邮箱地址
     * @param activationCode 激活码
     */
    void sendActivationMail(String maiTo, String activationCode);
}