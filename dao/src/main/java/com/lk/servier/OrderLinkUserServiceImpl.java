package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.OrderLinkUser;
import com.lk.mapper.OrderLinkUserMapper;
import com.lk.servier.impl.OrderLinkUserService;
import org.springframework.stereotype.Service;

@Service
public class OrderLinkUserServiceImpl extends ServiceImpl<OrderLinkUserMapper, OrderLinkUser> implements OrderLinkUserService{

}
