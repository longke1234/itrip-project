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
 * @description : 热门城市响应对象
 * @date : 2020-11-23 10:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(discriminator = "热门城市响应对象")
public class AreDicVo implements Serializable {

    private static final long serialVersionUID = 4014392509195186382L;

    @ApiModelProperty(name = "name",value = "热门城市id")
    private Long id;
    @ApiModelProperty(name = "name",value = "热门城市名称")
    private String name;
}