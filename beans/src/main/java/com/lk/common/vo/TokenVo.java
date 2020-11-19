package com.lk.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : token响应对象
 * @date : 2020-11-18 21:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "token响应对象")
public class TokenVo implements Serializable {

    private static final long serialVersionUID = -8616468393125282283L;

    @ApiModelProperty(value = "用户认证凭证")
    private String token;
    @ApiModelProperty(value = "过期时间，单位毫秒")
    private Long expTime;
    @ApiModelProperty(value = "生成时间，单位毫秒")
    private Long genTime;
}