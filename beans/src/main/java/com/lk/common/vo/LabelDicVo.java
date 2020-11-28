package com.lk.common.vo;

import io.swagger.annotations.Api;
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
 * @description : 酒店特色响应实体类
 * @date : 2020-11-24 10:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "通用字典响应实体类")
public class LabelDicVo implements Serializable {

    private static final long serialVersionUID = -1628020979931310195L;

    @ApiModelProperty(name = "id",value = "标签字典Id")
    private Long id;
    @ApiModelProperty(name = "name",value = "标签字典key值")
    private String name;
    @ApiModelProperty(name = "description",value = "标签字典描述")
    private String description;
    @ApiModelProperty(name = "pic",value = "标签图片地址")
    private String pic;


}