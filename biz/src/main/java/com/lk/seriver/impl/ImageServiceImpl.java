package com.lk.seriver.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.Image;
import com.lk.common.vo.ImageVo;
import com.lk.mapper.ImageMapper;
import com.lk.seriver.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    @Override
    public List<ImageVo> getImageVOByHotelId(Long hotelId) {
        //使用lambda表达式的条件
        LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        imageLambdaQueryWrapper.select(Image::getPosition, Image::getImgUrl)
                .eq(Image::getTargetId, hotelId)
                // 图片类型(0:酒店图片1:房间图片2:评论图片)
                .eq(Image::getType, "0");
        //执行条件查询
        List<Image> imageList = this.list(imageLambdaQueryWrapper);
        //执行数据类型转换 Image -> ImageVO
        List<ImageVo> imageVOList = imageList.stream().map(
                image -> {
                    ImageVo imageVO = new ImageVo();
                    imageVO.setPosition(image.getPosition());
                    imageVO.setImgUrl(image.getImgUrl());
                    return imageVO;
                }
        ).collect(Collectors.toList());
        return imageVOList;
    }
}
