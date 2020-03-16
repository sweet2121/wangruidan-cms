package com.wrd.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wrd.cms.domain.Category;
import com.wrd.cms.domain.Channel;
import com.wrd.cms.service.ChannelService;
/**
 * 
 * @ClassName: ChannelController 
 * @Description:  栏目的控制器
 * @author: 瑞
 * @date: 2020年3月5日 上午10:42:12
 */
@Controller
@RequestMapping("channel")
public class ChannelController {

	@Resource
	private ChannelService service;
	
	/**
	 * 
	 * @Title: channels 
	 * @Description: 查询所有的栏目
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@RequestMapping("channels")
	public List<Channel> channels(){
		
		return service.list();
	}
	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目查询分类
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@RequestMapping("selects")
	public List<Category> selects(Integer channelId){
		return service.selects(channelId);
	}
}
