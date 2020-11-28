package com.lk.seriver.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.ProductStore;
import com.lk.mapper.ProductStoreMapper;
import com.lk.seriver.ProductStoreService;
import org.springframework.stereotype.Service;

@Service
public class ProductStoreServiceImpl extends ServiceImpl<ProductStoreMapper, ProductStore> implements ProductStoreService {

}
