package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.Image;
import com.lk.mapper.ImageMapper;
import com.lk.servier.impl.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService{

}
