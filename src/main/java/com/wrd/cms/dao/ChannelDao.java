package com.wrd.cms.dao;
/**
 * 
 * @ClassName: ChannelDao 
 * @Description: 栏目
 * @author: 瑞
 * @date: 2020年3月5日 上午10:06:37
 */

import java.util.List;

import com.wrd.cms.domain.Category;
import com.wrd.cms.domain.Channel;

public interface ChannelDao {

	/**
	 * 
	 * @Title: list 
	 * @Description: 查询所有的栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> list();
	/**
	 * 
	 * @Title: selects 
	 * @Description:  根据栏目查询分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selects(Integer channelId);
}
