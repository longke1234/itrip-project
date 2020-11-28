package com.lk.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.bean.Hotel;
import com.lk.common.vo.SearchDetailHotelVo;
import com.lk.common.vo.SearchFacilitiesHotelVo;
import com.lk.common.vo.SearchPolicyHotelVo;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface HotelService extends IService<Hotel>{

    /**
     * 根据酒店id查询酒店设施
     */
    SearchFacilitiesHotelVo getHotelFacilitiesById(Long hotelId);

    /**
     *根据酒店id查询酒店政策
     * @param hotelId
     * @return
     */
    SearchPolicyHotelVo getHotelPolicyById(Long hotelId);

    /**
     * 根据酒店的id查询酒店的特色和介绍
     * @param hotelId
     * @return
     */
    List<SearchDetailHotelVo> getHotelDetailVoById(Long hotelId);



}
