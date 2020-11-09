package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.ProductStore;
import com.lk.mapper.ProductStoreMapper;
import com.lk.servier.impl.ProductStoreService;
import org.springframework.stereotype.Service;

@Service
public class ProductStoreServiceImpl extends ServiceImpl<ProductStoreMapper, ProductStore> implements ProductStoreService{

}
