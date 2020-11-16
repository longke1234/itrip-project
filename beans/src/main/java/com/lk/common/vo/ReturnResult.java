package com.lk.common.vo;

import com.baomidou.mybatisplus.extension.api.R;
import com.lk.common.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 公共返回类
 * @date : 2020-11-06 15:52
 */
@Data
@ApiModel(description = "错误结果返回类")
public class ReturnResult implements Serializable {
    private static final long serialVersionUID = 8149618151283184365L;

    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "错误码")
    private String errorCode;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    private ReturnResult(boolean success, String errorCode, String msg, Object data) {
        this.success = success;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 操作成功，指定返回信息和数据
     * @param msg
     * @param data
     * @return
     */
    public static ReturnResult ok(String msg,Object data){
        return new ReturnResult(true, ErrorCodeEnum.OK.getErrorCode(),msg,data);
    }

    /**
     * 操作成功，指定响应数据
     * @param data
     * @return
     */
    public static ReturnResult ok(Object data){
        return ReturnResult.ok(ErrorCodeEnum.OK.getMsg(),data);
    }

    /**
     * 操作成功，无响应
     * @return
     */
    public static ReturnResult ok(){
            return ReturnResult.ok(null);
    }

    /**
     * 操作失败，指定错误码，响应信息，相应数据
     * @param errorCode
     * @param msg
     * @param data
     * @return
     */
    public static ReturnResult error(String errorCode,String msg,Object data){
        return new ReturnResult(false,errorCode,msg,data);
    }

    public static ReturnResult error(String errorCode,String msg){
        return error(errorCode,msg,null);
    }

    /**
     * 操作失败，指定错误码枚举对象
     * @param errorCodeEnum
     * @return
     */
    public static ReturnResult error(ErrorCodeEnum errorCodeEnum){
        return error(errorCodeEnum.getErrorCode(),errorCodeEnum.getMsg());
    }

    /**
     * 操作失败，通用
     * @return
     */
    public static ReturnResult error(){
        return error(ErrorCodeEnum.FAILED);
    }


}