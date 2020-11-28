package com.lk.vo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.shape.VLineTo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 搜索酒店展示实体类
 * @date : 2020-11-27 10:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "hotel",type = "_doc")
@ApiModel(description = "搜索酒店展示实体类")
public class HotelVo implements Serializable {

    private static final long serialVersionUID = -3166744011057781686L;
    @Field(name = "id",type = FieldType.Long)
    @ApiModelProperty(value = "主键，酒店id")
    private Long id;

    @ApiModelProperty(value = "酒店名称")
    @Field(name = "hotelName",type = FieldType.Text)
    private String hotelName;

    @ApiModelProperty(value = "城市id")
    @Field(name = "cityId",type = FieldType.Long)
    private Long cityId;

    @ApiModelProperty(value = "酒店所在地址")
    @Field(name = "address",type = FieldType.Text)
    private String address;

    @ApiModelProperty(value ="酒店级别：1:经济酒店  2:二星级酒店  3:三星级 4:四星酒店 5星酒店" )
    @Field(name = "hotelLevel",type = FieldType.Text)
    private Integer hotelLevel;

    @ApiModelProperty(value = "城市名称")
    @Field(name = "redundantCityName",type = FieldType.Text)
    private String redundantCityName;

    @ApiModelProperty(value = "省名称")
    @Field(name = "redundantProvinceName",type = FieldType.Text)
    private String redundantProvinceName;

    @ApiModelProperty(value = "国家名称")
    @Field(name = "redundantCountryName",type = FieldType.Text)
    private String redundantCountryName;

    @ApiModelProperty(value = "酒店介绍")
    @Field(name = "details",type = FieldType.Text)
    private String details;

    @ApiModelProperty(value = "酒店设施")
    @Field(name = "facilities",type = FieldType.Text)
    private String facilities;

    @ApiModelProperty(value = "酒店政策")
    @Field(name = "hotelPolicy",type = FieldType.Text)
    private String hotelPolicy;

    @ApiModelProperty(value = "最高价格")
    @Field(name = "maxPrice",type = FieldType.Double)
    private Double maxPrice;

    @ApiModelProperty(value = "最低价格")
    @Field(name = "minPrice",type = FieldType.Double)
    private Double minPrice;

    @ApiModelProperty(value = "特色id")
    @Field(name = "featureId",type = FieldType.Text)
    private String featureId;

    @ApiModelProperty(value = "交易名称")
    @Field(name = "tradingAreaNames",type = FieldType.Text)
    private String tradingAreaNames;

    @ApiModelProperty(value = "特色名称")
    @Field(name = "featureNames",type = FieldType.Text)
    private String featureNames;

    @Field(name = "isOkCount" ,type = FieldType.Integer)
    private Integer isOkCount;

    @ApiModelProperty(value = "评论条数")
    @Field(name = "commentCount",type = FieldType.Integer)
    private Integer commentCount;

    @ApiModelProperty(value = "平均评分")
    @Field(name = "avgScore",type =FieldType.Double)
    private Double avgScore;

    @ApiModelProperty(value = "图片路径")
    @Field(name = "imgUrl",type = FieldType.Text)
    private String imgUrl;

}