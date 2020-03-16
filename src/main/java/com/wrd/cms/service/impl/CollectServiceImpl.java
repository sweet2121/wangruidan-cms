package com.wrd.cms.service.impl;

import java.awt.color.CMMException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrd.cms.dao.CollectDao;
import com.wrd.cms.domain.Article;
import com.wrd.cms.domain.Collect;
import com.wrd.cms.domain.User;
import com.wrd.cms.service.CollectService;
import com.wrd.common.utils.StringUtil;

@Service
public class CollectServiceImpl implements CollectService {

	@Resource
	private CollectDao dao;

	@Override
	public int insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl())){
			throw new CMMException("不是合法的url");
		}
		return dao.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		
		return dao.delete(id);
	}

	@Override
	public List<Collect> selects(Integer userId) {
		// TODO Auto-generated method stub
		return dao.selects(userId);
	}

	@Override
	public Collect selectByTitleAndUserId(String title, Integer userId) {
		// TODO Auto-generated method stub
		return dao.selectByTitleAndUserId(title, userId);
	}

	@Override
	public PageInfo<Collect> list(User user, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Collect> list=dao.list(user);
		return new PageInfo<Collect>(list);
	}
	

	
	
}
