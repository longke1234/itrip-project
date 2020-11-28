package com.lk.seriver;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 阿里云短信service验证
 * @date : 2020-11-16 10:05
 */
public interface SmsService {
    /**
     * 用于发送短信
     * @param to 收信人
     * @param code 验证码
     */
    void sendMsg(String to, String code);
}