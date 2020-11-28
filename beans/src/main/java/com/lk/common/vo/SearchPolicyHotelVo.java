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
 * @description : 酒店政策展示实体类
 * @date : 2020-11-25 13:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "酒店政策展示实体类")
public class SearchPolicyHotelVo implements Serializable {

    private static final long serialVersionUID = 8476007097078378898L;

    @ApiModelProperty(name = "policyHotel",value = "酒店政策")

    private String policyHotel;
}