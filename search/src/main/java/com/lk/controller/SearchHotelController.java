package com.lk.controller;

import com.lk.common.vo.ReturnResult;
import com.lk.condition.SearchHotCityCondition;
import com.lk.condition.SearchHotelCondition;
import com.lk.service.SearchHotelService;
import com.lk.vo.HotelVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 热门城市酒店控制器
 * @date : 2020-11-27 11:28
 */
@ApiModel(description = "热门城市酒店控制器")
@RestController
@RequestMapping(value = "/api/hotellist")
public class SearchHotelController {

    @Resource
    private SearchHotelService searchHotelService;

    @ApiOperation(value = "根据热门城市查询酒店", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "根据热门城市查询酒店")
    @RequestMapping(value = "/searchItripHotelListByHotCity",
            produces = MediaType.APPLICATION_JSON_VALUE, method =
            RequestMethod.POST)
    public ReturnResult searchHotelListByHotCity(@RequestBody SearchHotCityCondition condition) {
        List<HotelVo> hotelVOList = searchHotelService.searchHotelByHotelCity(condition.getCityId(), condition.getCount());
        return ReturnResult.ok(hotelVOList);
    }

    /**
     * 搜索酒店分页
     *
     * @param condition
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店分页", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "查询酒店分页(用于查询酒店列表)")
    @RequestMapping(value = "/searchItripHotelPage",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult searchHotelPage(@RequestBody SearchHotelCondition condition)
    {
        Page<HotelVo> hotelVOPage = searchHotelService.searchHotelPage(condition);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("rows", hotelVOPage.getContent());
        return ReturnResult.ok(returnMap);
    }


}