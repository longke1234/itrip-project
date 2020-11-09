package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.mapper.ImageMapper;
import com.lk.bean.Image;
import com.lk.servier.impl.ImageService;
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService{

}
