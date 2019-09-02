package com.ecpss.service.impl;

import com.ecpss.dao.CommentDao;
import com.ecpss.dao.CommentService;
import com.ecpss.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by xc on 2019/8/27.
 */
@Service
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    
    @Override
    public List<Comment> listCommentByBlogId(Integer id) {//得到所有的评论
        return null;
    }
    
    @Override
    public void saveComment(Comment comment) {
        Integer parentCommentId = comment.getId();
        if (parentCommentId != -1) {
            comment.setParent_comment_id(parentCommentId);
        } else {
            comment.setParent_comment_id(0);
        }
        comment.setCreateTime(new Date());
        commentDao.saveComment(comment);
    }
}
