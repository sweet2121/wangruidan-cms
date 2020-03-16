package com.wrd.cms.dao;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import com.wrd.cms.domain.User;

public interface UserDao {

	//用户列表
	List<User> selects(User user);
	//更新
	int update(User user);
	//增加
	int insert(User user);
	//用戶名是否存在
	User selectByUsername(String username);
	
}
