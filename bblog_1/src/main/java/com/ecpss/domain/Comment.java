package com.ecpss.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xc on 2019/8/24.
 */
@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private Integer blog_id;
    
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    private Integer parent_comment_id;
//    是否是管理员评论
    @Column(columnDefinition = "integer default 0")
    private boolean adminComment;
}
