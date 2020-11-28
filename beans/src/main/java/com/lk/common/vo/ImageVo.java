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
 * @description : 酒店图片展示实体类
 * @date : 2020-11-25 18:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "酒店图片展示实体类")
public class ImageVo implements Serializable {

    private static final long serialVersionUID = -8408522485966811633L;

    @ApiModelProperty(value = "页面图片展现顺序")
    private Integer position;

    @ApiModelProperty(value = "图片的URL访问路径")
    private String imgUrl;

}