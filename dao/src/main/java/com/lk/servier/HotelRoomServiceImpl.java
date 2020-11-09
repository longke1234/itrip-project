package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.HotelRoom;
import com.lk.mapper.HotelRoomMapper;
import com.lk.servier.impl.HotelRoomService;
import org.springframework.stereotype.Service;

@Service
public class HotelRoomServiceImpl extends ServiceImpl<HotelRoomMapper, HotelRoom> implements HotelRoomService{

}
