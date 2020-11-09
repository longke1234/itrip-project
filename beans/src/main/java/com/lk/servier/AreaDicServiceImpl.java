package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.AreaDic;
import com.lk.mapper.AreaDicMapper;
import com.lk.servier.impl.AreaDicService;
@Service
public class AreaDicServiceImpl extends ServiceImpl<AreaDicMapper, AreaDic> implements AreaDicService{

}
