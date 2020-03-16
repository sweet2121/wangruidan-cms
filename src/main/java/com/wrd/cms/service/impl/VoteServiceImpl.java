package com.wrd.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wrd.cms.dao.VoteDao;
import com.wrd.cms.domain.Vote;
import com.wrd.cms.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

	@Resource 
	private VoteDao dao;

	@Override
	public int insert(Vote vote) {
		// TODO Auto-generated method stub
		return dao.insert(vote);
	}

	@Override
	public List<Vote> selects(Integer articleId) {
		List<Vote> list = dao.selects(articleId);
		
		return list;
	}

	@Override
	public Vote select(Vote vote) {
		// TODO Auto-generated method stub
		return dao.select(vote);
	}
	
	

}
