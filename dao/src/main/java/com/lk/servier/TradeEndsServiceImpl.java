package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.TradeEnds;
import com.lk.mapper.TradeEndsMapper;
import com.lk.servier.impl.TradeEndsService;
import org.springframework.stereotype.Service;

@Service
public class TradeEndsServiceImpl extends ServiceImpl<TradeEndsMapper, TradeEnds> implements TradeEndsService{

}
