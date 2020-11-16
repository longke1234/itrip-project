package com.lk.common.exception;

import com.lk.common.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义Dao层异常
 * @date : 2020-11-10 11:01
 */
@Data
@ApiModel(description = "dao层自定义异常")
public class DaoException extends RuntimeException{

    @ApiModelProperty(value = "异常状态码")
    private String errorCode;

    public DaoException(String msg,String errorCode){
        super(msg);
        this.errorCode = errorCode;
    }

    public DaoException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMsg());
        this.errorCode=errorCodeEnum.getErrorCode();
    }
}