package com.ecpss.dao.impl;

import com.ecpss.dao.CommentDao;
import com.ecpss.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xc on 2019/8/27.
 */
@Slf4j
@Repository
@Transactional
public class CommentDaoImpl extends DefaultDaoImpl implements CommentDao{
    
    @Override
    public List<Comment> listCommentByBlogId(Integer id) {
        
        return null;
    }
    
    @Override
    public void saveComment(Comment comment) {
        if (comment.getId() == null) {
            log.info("这是增加 id={}", comment.getId());
            em.persist(comment);
        } else {
            log.info("这是修改 id={}", comment.getId());
            em.merge(comment);
        }
    
    }
}
