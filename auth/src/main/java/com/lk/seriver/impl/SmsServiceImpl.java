package com.lk.seriver.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.exception.ServiceException;
import com.lk.seriver.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 阿里云短信验证实现类
 * @date : 2020-11-16 10:13
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendMsg(String to, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
        //这里我们直接写死用户的accessKeyId和secret
                "LTAI4G2MYsU7eNoyZgTVqGXK",
                "bJLpoUE6XjczQfFZKGhDtJaXQ1aUXT");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //指定发送的号码，也就是用户注册的号码
        request.putQueryParameter("PhoneNumbers", to);
        //指定短信中的前缀
        request.putQueryParameter("SignName", "爱旅行");
        request.putQueryParameter("TemplateCode", "SMS_204111297");
        //指定变量，也就是生成的code
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", code);
        String templateParamJson = JSON.toJSONString(jsonMap);
        request.putQueryParameter("TemplateParam", templateParamJson);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("响应数据：{}", response.getData());
        } catch (ServerException e) {
            log.error(e.getMessage(), e);
            throw new
                    ServiceException(ErrorCodeEnum.ERROR_CALLING_THIRD_PARTY_SERVICE);
        } catch (ClientException e) {
            log.error(e.getMessage(), e);
            throw new
                    ServiceException(ErrorCodeEnum.ERROR_CALLING_THIRD_PARTY_SERVICE);
        }
    }
}