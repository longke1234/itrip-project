package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.TradeEnds;
import com.lk.mapper.TradeEndsMapper;
import com.lk.servier.impl.TradeEndsService;
@Service
public class TradeEndsServiceImpl extends ServiceImpl<TradeEndsMapper, TradeEnds> implements TradeEndsService{

}
