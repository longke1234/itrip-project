package com.lk.condition;

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
 * @description : 根据热门城市搜索酒店条件类
 * @date : 2020-11-27 11:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "根据热门城市搜索酒店条件类")
public class SearchHotCityCondition implements Serializable {

    private static final long serialVersionUID = -3703330463421066169L;

    @ApiModelProperty(value = "城市id")
    private Long cityId;

    @ApiModelProperty(value = "数据条数")
    private Long count;
}