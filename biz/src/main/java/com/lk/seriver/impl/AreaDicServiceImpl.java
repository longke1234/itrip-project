package com.lk.seriver.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.AreaDic;
import com.lk.common.constants.ErrorCodeEnum;
import com.lk.common.exception.ServiceException;
import com.lk.common.vo.AreDicVo;
import com.lk.mapper.AreaDicMapper;
import com.lk.seriver.AreaDicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaDicServiceImpl extends ServiceImpl<AreaDicMapper, AreaDic> implements AreaDicService {

    @Override
    public List<AreDicVo> findAreaDicVoById(Integer type) {
        LambdaQueryWrapper<AreaDic> areaDicLambdaQueryWrapper = new QueryWrapper<AreaDic>().lambda();
        areaDicLambdaQueryWrapper.select(AreaDic::getId,AreaDic::getName)
                // TODO: 2020-11-24 0:是,1:不是 
                .eq(AreaDic::getIsHot,1)
                //国内，国外
                .eq(AreaDic::getIsChina,type);
        List<AreaDic> areaDicList = this.list(areaDicLambdaQueryWrapper);
        if (StringUtils.isEmpty(areaDicList)) {
            //当数据为空，直接抛出异常
            throw new ServiceException(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        //完成类型转换
        List<AreDicVo> areDicVoList = areaDicList.stream().map(
                areaDic -> {
                    AreDicVo areDicVo = new AreDicVo();
                    //copy数据 areaDic -》areDicVo
                    BeanUtils.copyProperties(areaDic, areDicVo);
                    return areDicVo;
                }).collect(Collectors.toList());
        return areDicVoList;
    }

    @Override
    public List<AreDicVo> findAreaDicVoByCityId(Long cityId) {
        LambdaQueryWrapper<AreaDic> areaDicLambdaQueryWrapper = new QueryWrapper<AreaDic>().lambda();
        areaDicLambdaQueryWrapper.select(AreaDic::getId, AreaDic::getName)
                //是否是商圈(0:不是 1:是)
                .eq(AreaDic::getIsTradingArea,1)
                //父级区域
                .eq(AreaDic::getParent,cityId);
        List<AreaDic> areaDicList = this.list(areaDicLambdaQueryWrapper);
        if (CollectionUtils.isEmpty(areaDicList)) {
            throw new ServiceException(ErrorCodeEnum.BIZ_DATA_NULL);
        }
        //完成类型转换
        List<AreDicVo> areaDicLists = areaDicList.stream().map(
                areaDic -> {
                    AreDicVo areDicVo = new AreDicVo();
                    BeanUtils.copyProperties(areaDic, areDicVo);
                    return areDicVo;
                }
        ).collect(Collectors.toList());
        return areaDicLists;
    }
}
