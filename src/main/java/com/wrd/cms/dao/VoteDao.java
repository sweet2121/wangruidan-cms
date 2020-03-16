package com.wrd.cms.dao;

import java.util.List;

import com.wrd.cms.domain.Vote;

/**
 * 
 * @ClassName: VoteMapper 
 * @Description: 投票
 * @author: charles
 * @date: 2020年3月15日 下午12:07:51
 */
public interface VoteDao {

	//添加
	int insert(Vote vote);
	
	//查询某个文章的投票情况.一个文章可能被投票多次
	List<Vote> selects(Integer articleId);
	
	
	//查看用户是否重复投票
	Vote select(Vote vote);
}
