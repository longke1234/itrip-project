package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.HotelTempStore;
import com.lk.mapper.HotelTempStoreMapper;
import com.lk.servier.impl.HotelTempStoreService;
import org.springframework.stereotype.Service;

@Service
public class HotelTempStoreServiceImpl extends ServiceImpl<HotelTempStoreMapper, HotelTempStore> implements HotelTempStoreService{

}
