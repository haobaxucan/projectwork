package com.ecpss.service;

import com.ecpss.dao.VoteRepository;
import com.ecpss.spring.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Vote 服务.
 * 
 * @since 1.0.0 2017年4月9日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;
	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.blog.service.VoteService#removeVote(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeVote(Long id) {
		
//		voteRepository.delete(id);
	}
	@Override
	public Vote getVoteById(Long id) {
		return null;
//		return voteRepository.findOne(id);
	}
	

}
