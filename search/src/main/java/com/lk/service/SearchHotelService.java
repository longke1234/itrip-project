package com.lk.service;

import com.lk.condition.SearchHotelCondition;
import com.lk.vo.HotelVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 搜索酒店service接口
 * @date : 2020-11-27 11:09
 */
public interface SearchHotelService {

    /**
     * 根据热门城市查询酒店
     * @param cityId
     * @param pageSize
     * @return
     */
    List<HotelVo> searchHotelByHotelCity(Long cityId,Long pageSize);

    /**
     * 搜索酒店并分页
     * @param condition
     * @return
     */
    Page<HotelVo> searchHotelPage(SearchHotelCondition condition);
}