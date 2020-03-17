package com.wrd.cms.dao;

import java.util.List;

import com.wrd.cms.domain.ChooseResult;

public interface ChooseResultDao {

	//添加
	int insert(ChooseResult chooseResult);
	//查询某个文章的投票情况.一个文章可能被投票多次
	List<ChooseResult> selects(Integer articleId);
	//查看用户是否重复投票
	ChooseResult select(ChooseResult chooseResult);
}
