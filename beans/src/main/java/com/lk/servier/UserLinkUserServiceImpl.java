package com.lk.servier;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.UserLinkUser;
import com.lk.mapper.UserLinkUserMapper;
import com.lk.servier.impl.UserLinkUserService;
@Service
public class UserLinkUserServiceImpl extends ServiceImpl<UserLinkUserMapper, UserLinkUser> implements UserLinkUserService{

}
