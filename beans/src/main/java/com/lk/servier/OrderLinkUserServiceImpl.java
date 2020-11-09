package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.mapper.OrderLinkUserMapper;
import com.lk.bean.OrderLinkUser;
import com.lk.servier.impl.OrderLinkUserService;
@Service
public class OrderLinkUserServiceImpl extends ServiceImpl<OrderLinkUserMapper, OrderLinkUser> implements OrderLinkUserService{

}
