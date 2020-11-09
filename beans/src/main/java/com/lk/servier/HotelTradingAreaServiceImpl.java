package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.HotelTradingArea;
import com.lk.mapper.HotelTradingAreaMapper;
import com.lk.servier.impl.HotelTradingAreaService;
@Service
public class HotelTradingAreaServiceImpl extends ServiceImpl<HotelTradingAreaMapper, HotelTradingArea> implements HotelTradingAreaService{

}
