package com.lk.common.exception;

import com.lk.common.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义系统异常
 * @date : 2020-11-10 11:19
 */
@Data
@ApiModel(description = "自定义系统异常")
public class SysException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private String errorCode;

    public SysException(String msg,String errorCode){
        super(msg);
        this.errorCode=errorCode;
    }

    public SysException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMsg());
        this.errorCode=errorCodeEnum.getErrorCode();
    }
}