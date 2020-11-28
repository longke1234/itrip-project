package com.lk.seriver.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.LabelDic;
import com.lk.common.vo.LabelDicVo;
import com.lk.mapper.LabelDicMapper;
import com.lk.seriver.LabelDicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabelDicServiceImpl extends ServiceImpl<LabelDicMapper, LabelDic> implements LabelDicService {

    @Override
    public List<LabelDicVo> fidLabelDicVo() {
        LambdaQueryWrapper<LabelDic> labelDicLambdaQueryWrapper = new QueryWrapper<LabelDic>().lambda();
        labelDicLambdaQueryWrapper.select(LabelDic::getId,LabelDic::getName,LabelDic::getDescription,LabelDic::getPic)
                //16 特色酒店
                .eq(LabelDic::getParentId,16);
        List<LabelDic> labelDicList = this.list(labelDicLambdaQueryWrapper);
        //类型装换
        List<LabelDicVo> labelDicVos = labelDicList.stream().map(
                labelDic -> {
                    LabelDicVo labelDicVo = new LabelDicVo();
                    BeanUtils.copyProperties(labelDic, labelDicVo);
                    return labelDicVo;
                }
        ).collect(Collectors.toList());
        return labelDicVos;
    }
}
