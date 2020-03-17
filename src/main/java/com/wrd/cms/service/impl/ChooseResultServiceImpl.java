package com.wrd.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wrd.cms.dao.ChooseResultDao;
import com.wrd.cms.domain.ChooseResult;
import com.wrd.cms.service.ChooseResultService;



@Service
public class ChooseResultServiceImpl implements ChooseResultService {

	@Resource
	private ChooseResultDao dao;

	@Override
	public int insert(ChooseResult chooseResult) {
		// TODO Auto-generated method stub
		return dao.insert(chooseResult);
	}

	@Override
	public List<ChooseResult> selects(Integer articleId) {
		List<ChooseResult> list = dao.selects(articleId);
		return list;
	}

	@Override
	public ChooseResult select(ChooseResult chooseResult) {
		// TODO Auto-generated method stub
		return dao.select(chooseResult);
	}
	

}
