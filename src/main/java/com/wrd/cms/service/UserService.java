package com.wrd.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wrd.cms.domain.User;

public interface UserService {

	//用户列表
	PageInfo<User> selects(User user,Integer page,Integer pageSize);
	//修改
	int update(User user);
	
	//增加
	int insert(User user);
	//用戶名是否存在
	User selectByUsername(String username);
	//登录
	User login(User user);
}
