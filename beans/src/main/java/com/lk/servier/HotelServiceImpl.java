package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.Hotel;
import com.lk.mapper.HotelMapper;
import com.lk.servier.impl.HotelService;
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService{

}
