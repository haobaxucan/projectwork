package com.ecpss.dao;

import com.ecpss.domain.Comment;

import java.util.List;

/**
 * Created by xc on 2019/8/27.
 */
public interface CommentService {
    
    List<Comment> listCommentByBlogId(Integer id);
    void saveComment(Comment comment);
}
