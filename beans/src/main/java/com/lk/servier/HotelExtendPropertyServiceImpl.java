package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.mapper.HotelExtendPropertyMapper;
import com.lk.bean.HotelExtendProperty;
import com.lk.servier.impl.HotelExtendPropertyService;
@Service
public class HotelExtendPropertyServiceImpl extends ServiceImpl<HotelExtendPropertyMapper, HotelExtendProperty> implements HotelExtendPropertyService{

}
