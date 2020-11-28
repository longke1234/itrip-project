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
 * @description : 酒店设施展示实体类
 * @date : 2020-11-24 11:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "酒店设施展示实体类")
public class SearchFacilitiesHotelVo implements Serializable {

    private static final long serialVersionUID = 6768308349908338785L;

    @ApiModelProperty(value ="\"[必填] 酒店设施")
    private String facilities;

}