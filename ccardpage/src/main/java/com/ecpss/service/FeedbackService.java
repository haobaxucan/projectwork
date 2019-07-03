package com.ecpss.service;

import com.ecpss.domain.Feedback;
import com.ecpss.exception.Response;

/**
 * Created by xc on 2019/6/25.
 */
public interface FeedbackService {
    
    /**
     * <h2>创建评论</h2>
     * @param feedback {@link Feedback}
     * @return {@link Response}
     * */
    Response createFeedback(Feedback feedback);
    
    /**
     * <h2>获取用户评论</h2>
     * @param userId 用户 id
     * @return {@link Response}
     * */
    Response getFeedback(Long userId);
}
