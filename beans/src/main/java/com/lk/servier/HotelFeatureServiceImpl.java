package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.mapper.HotelFeatureMapper;
import com.lk.bean.HotelFeature;
import com.lk.servier.impl.HotelFeatureService;
@Service
public class HotelFeatureServiceImpl extends ServiceImpl<HotelFeatureMapper, HotelFeature> implements HotelFeatureService{

}
