package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.HotelTempStore;
import com.lk.mapper.HotelTempStoreMapper;
import com.lk.servier.impl.HotelTempStoreService;
@Service
public class HotelTempStoreServiceImpl extends ServiceImpl<HotelTempStoreMapper, HotelTempStore> implements HotelTempStoreService{

}
