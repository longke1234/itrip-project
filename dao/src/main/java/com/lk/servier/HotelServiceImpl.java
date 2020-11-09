package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.Hotel;
import com.lk.mapper.HotelMapper;
import com.lk.servier.impl.HotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService{

}
