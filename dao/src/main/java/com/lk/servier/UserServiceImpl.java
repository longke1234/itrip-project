package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.User;
import com.lk.mapper.UserMapper;
import com.lk.servier.impl.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
