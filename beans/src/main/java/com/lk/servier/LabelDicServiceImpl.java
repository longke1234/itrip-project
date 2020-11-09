package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.LabelDic;
import com.lk.mapper.LabelDicMapper;
import com.lk.servier.impl.LabelDicService;
@Service
public class LabelDicServiceImpl extends ServiceImpl<LabelDicMapper, LabelDic> implements LabelDicService{

}
