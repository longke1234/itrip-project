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
import com.lk.util.JacksonUtil;
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
                "LTAI4FzaGhcjkHKd2LaQh6vB",
                "RVNlvIRFRzLq3ahRqnvQ87hpQJNnoI");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //指定发送的号码，也就是用户注册的号码 需要修改
        request.putQueryParameter("PhoneNumbers", to);
        //指定短信中的前缀 需要修改
        request.putQueryParameter("SignName", "爱旅行");
        //模板编码 需要修改
        request.putQueryParameter("TemplateCode", "SMS_205466323");
        //指定变量，也就是生成的code
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", code);
        // TODO: 2020-11-17 fastjson有漏洞，不推荐使用
//        String templateParamJson = JSON.toJSONString(jsonMap);
        String templateParamJson = JacksonUtil.objectToJsonString(jsonMap);
        request.putQueryParameter("TemplateParam", templateParamJson);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("响应数据：{}", response.getData());
        } catch (ServerException e) {
            log.error(e.getMessage(), e);
            //调用第三方服务出错,封装成统一的异常并抛出
            throw new ServiceException(ErrorCodeEnum.ERROR_CALLING_THIRD_PARTY_SERVICE);
        } catch (ClientException e) {
            log.error(e.getMessage(), e);
            //调用第三方服务出错，封装成统一的异常并抛出
            throw new ServiceException(ErrorCodeEnum.ERROR_CALLING_THIRD_PARTY_SERVICE);
        }
    }
}