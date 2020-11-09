package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.HotelOrder;
import com.lk.mapper.HotelOrderMapper;
import com.lk.servier.impl.HotelOrderService;
import org.springframework.stereotype.Service;

@Service
public class HotelOrderServiceImpl extends ServiceImpl<HotelOrderMapper, HotelOrder> implements HotelOrderService{

}
