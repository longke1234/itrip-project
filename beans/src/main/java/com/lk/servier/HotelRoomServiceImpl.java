package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.mapper.HotelRoomMapper;
import com.lk.bean.HotelRoom;
import com.lk.servier.impl.HotelRoomService;
@Service
public class HotelRoomServiceImpl extends ServiceImpl<HotelRoomMapper, HotelRoom> implements HotelRoomService{

}
