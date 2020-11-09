package com.lk.excption;

import io.swagger.annotations.ApiModelProperty;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义dao层异常类
 * @date : 2020-11-09 14:48
 */
public class DaoException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private String errorCode;

    public DaoException(String message,String errorCode){
        super(message);
        this.errorCode=errorCode;
    }

}