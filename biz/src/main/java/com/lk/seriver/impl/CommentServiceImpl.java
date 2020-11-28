package com.lk.seriver.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.bean.Comment;
import com.lk.mapper.CommentMapper;
import com.lk.seriver.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
