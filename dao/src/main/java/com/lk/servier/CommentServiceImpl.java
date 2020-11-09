package com.lk.servier;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.Comment;
import com.lk.mapper.CommentMapper;
import com.lk.servier.impl.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

}
