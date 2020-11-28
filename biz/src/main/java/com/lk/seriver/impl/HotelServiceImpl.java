package com.lk.seriver.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.Hotel;
import com.lk.bean.LabelDic;
import com.lk.common.vo.SearchDetailHotelVo;
import com.lk.common.vo.SearchFacilitiesHotelVo;
import com.lk.common.vo.SearchPolicyHotelVo;
import com.lk.mapper.HotelMapper;
import com.lk.seriver.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

    @Override
    public SearchFacilitiesHotelVo getHotelFacilitiesById(Long hotelId) {
        return baseMapper.searchFacilitiesHotelVoByHotelId(hotelId);
    }

    @Override
    public SearchPolicyHotelVo getHotelPolicyById(Long policyId) {
        return baseMapper.searchHotelPolicyByHotelId(policyId);
    }

    @Override
    public List<SearchDetailHotelVo> getHotelDetailVoById(Long hotelId) {
        //1、特色需要通过多表联查的方式实现
        List<LabelDic> labelDicList =
                baseMapper.selectDetailHotelVoBuHotelId(
                        hotelId);
        //2、酒店介绍，可以直接查询
        LambdaQueryWrapper<Hotel> hotelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        hotelLambdaQueryWrapper.eq(Hotel::getId, hotelId)
                .select(Hotel::getHotelName, Hotel::getDetails);
        Hotel hotel = this.getOne(hotelLambdaQueryWrapper);
        //数据封装，特色
        List<SearchDetailHotelVo> detailHotelVOList = labelDicList.stream().map(
                labelDic -> {
                    SearchDetailHotelVo searchDetailHotelVO = new SearchDetailHotelVo();
                    searchDetailHotelVO.setDescription(labelDic.getDescription());
                    searchDetailHotelVO.setName(labelDic.getName());
                    return searchDetailHotelVO;
                }
        ).collect(Collectors.toList());
        //将介绍封装进来
        SearchDetailHotelVo searchDetailHotelVO = new SearchDetailHotelVo();
        searchDetailHotelVO.setName(hotel.getHotelName());
        searchDetailHotelVO.setDescription(hotel.getDetails());
        detailHotelVOList.add(searchDetailHotelVO);
        return detailHotelVOList;
    }
}
