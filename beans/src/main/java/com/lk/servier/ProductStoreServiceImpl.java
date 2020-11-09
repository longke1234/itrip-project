package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.ProductStore;
import com.lk.mapper.ProductStoreMapper;
import com.lk.servier.impl.ProductStoreService;
@Service
public class ProductStoreServiceImpl extends ServiceImpl<ProductStoreMapper, ProductStore> implements ProductStoreService{

}
