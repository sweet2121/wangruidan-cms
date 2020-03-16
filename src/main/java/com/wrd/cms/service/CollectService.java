package com.wrd.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.wrd.cms.domain.Collect;
import com.wrd.cms.domain.User;

public interface CollectService {

	//添加收藏
	int insert(Collect collect);
	//取消收藏
	int delete(Integer id);
	//显示我的收藏
	List<Collect> selects(Integer userId);
	//根据title和userid 查询此文章是否被该用户收藏过
	Collect selectByTitleAndUserId(@Param("title")String title,@Param("userId")Integer userId);
	//我的收藏
	PageInfo<Collect> list(User user, Integer page, Integer pageSize);
}
