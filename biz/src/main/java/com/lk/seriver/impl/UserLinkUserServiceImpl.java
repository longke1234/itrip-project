package com.lk.seriver.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.UserLinkUser;
import com.lk.mapper.UserLinkUserMapper;
import com.lk.seriver.UserLinkUserService;
import org.springframework.stereotype.Service;

@Service
public class UserLinkUserServiceImpl extends ServiceImpl<UserLinkUserMapper, UserLinkUser> implements UserLinkUserService {

}
