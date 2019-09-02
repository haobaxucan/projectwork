package com.ecpss.controller;

import com.ecpss.dao.CommentService;
import com.ecpss.domain.Comment;
import com.ecpss.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by xc on 2019/8/27.
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Integer blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }
//    每一个在博客评论的时候传入id
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Integer blogId = comment.getBlog_id();
        comment.setBlog_id(blogId);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());//头像
            comment.setAdminComment(true);
        } else {
            comment.setAvatar("");
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
    
    
}
