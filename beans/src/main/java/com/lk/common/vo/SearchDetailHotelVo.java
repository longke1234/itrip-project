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
 * @description : 酒店的特色和介绍展示实体类
 * @date : 2020-11-25 14:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "酒店的特色和介绍展示实体类")
public class SearchDetailHotelVo implements Serializable {

    private static final long serialVersionUID = -3975688969966696312L;

    @ApiModelProperty(name = "name",value = "酒店名称")
    private String name;

    @ApiModelProperty(name = "details",value = "酒店介绍")
    private String description;
}