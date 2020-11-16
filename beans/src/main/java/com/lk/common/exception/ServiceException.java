package com.lk.common.exception;

import com.lk.common.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义Service异常
 * @date : 2020-11-10 11:13
 */
@Data
@ApiModel(description = "service自定义异常")
public class ServiceException extends RuntimeException{

    @ApiModelProperty(value = "自定义异常状态码")
    private  String errorCode;

    public ServiceException(String msg,String errorCode){
        super(msg);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.getErrorCode();
    }
}