package com.lk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lk.bean.Hotel;
import com.lk.bean.LabelDic;
import com.lk.common.vo.SearchFacilitiesHotelVo;
import com.lk.common.vo.SearchPolicyHotelVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelMapper extends BaseMapper<Hotel> {
    /**
     * 根据酒店id查询酒店设施
     * @param hotelId
     * @return
     */
    SearchFacilitiesHotelVo searchFacilitiesHotelVoByHotelId(@Param("hotelId")Long hotelId);

    /**
     * 根据酒店id查询酒店政策
     * @param hotelId
     * @return
     */
    SearchPolicyHotelVo searchHotelPolicyByHotelId(@Param("hotelId")Long hotelId);

    /**
     * 根据酒店id查询酒店特色
     * @param hotelId
     * @return
     */
    List<LabelDic> selectDetailHotelVoBuHotelId(@Param("hotelId")Long hotelId);
}