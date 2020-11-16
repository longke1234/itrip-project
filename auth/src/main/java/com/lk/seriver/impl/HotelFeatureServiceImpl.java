package com.lk.seriver.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.HotelFeature;
import com.lk.mapper.HotelFeatureMapper;
import com.lk.seriver.HotelFeatureService;
import org.springframework.stereotype.Service;

@Service
public class HotelFeatureServiceImpl extends ServiceImpl<HotelFeatureMapper, HotelFeature> implements HotelFeatureService {

}
