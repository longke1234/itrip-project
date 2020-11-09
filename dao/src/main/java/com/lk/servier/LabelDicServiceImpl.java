package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.LabelDic;
import com.lk.mapper.LabelDicMapper;
import com.lk.servier.impl.LabelDicService;
import org.springframework.stereotype.Service;

@Service
public class LabelDicServiceImpl extends ServiceImpl<LabelDicMapper, LabelDic> implements LabelDicService{

}
