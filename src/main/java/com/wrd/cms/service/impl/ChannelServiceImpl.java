package com.wrd.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wrd.cms.dao.ChannelDao;
import com.wrd.cms.domain.Category;
import com.wrd.cms.domain.Channel;
import com.wrd.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Resource
	private ChannelDao dao;
	@Override
	public List<Channel> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	@Override
	public List<Category> selects(Integer channelId) {
		// TODO Auto-generated method stub
		return dao.selects(channelId);
	}

}
