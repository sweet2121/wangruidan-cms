package com.wrd.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wrd.cms.domain.Collect;
import com.wrd.cms.domain.User;

public interface CollectDao {

	//添加收藏
	int insert(Collect collect);
	//取消收藏
	int delete(Integer id);
	//显示我的收藏
	List<Collect> selects(Integer userId);
	//根据title和userid 查询此文章是否被该用户收藏过
	Collect selectByTitleAndUserId(@Param("title")String title,@Param("userId")Integer userId);
	//收藏文章
	List<Collect> list(User user);
}
