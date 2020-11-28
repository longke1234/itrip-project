package com.lk.seriver;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.bean.Image;
import com.lk.common.vo.ImageVo;

import java.util.List;

public interface ImageService extends IService<Image>{

    /**
     * 根据酒店Id查询图片
     * @param hotelId
     * @return
     */
    List<ImageVo> getImageVOByHotelId(Long hotelId);

}
