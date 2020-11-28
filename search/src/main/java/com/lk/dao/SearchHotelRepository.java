package com.lk.dao;

import com.lk.vo.HotelVo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author : lk
 * @version : 4.0
 * @project : itrip-project
 * @description : 搜索酒店dao层接口
 * @date : 2020-11-27 10:18
 */
public interface SearchHotelRepository extends ElasticsearchRepository<HotelVo,Long> {

    /**
     * 根据城市id查询酒店
     * @param cityId
     * @return
     */
    List<HotelVo> findByCityId(Long cityId);


}