package com.wrd.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wrd.cms.dao.SlideDao;
import com.wrd.cms.domain.Slide;
import com.wrd.cms.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {

	@Resource
	private SlideDao dao;
	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return dao.selects();
	}

}
