package com.wrd.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding.Use;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrd.cms.dao.UserDao;
import com.wrd.cms.domain.User;
import com.wrd.cms.service.UserService;
import com.wrd.cms.util.CMSException;
import com.wrd.cms.util.Md5Util;
import com.wrd.common.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Override
	public PageInfo<User> selects(User user, Integer page, Integer pageSize) {
		
		PageHelper.startPage(page, pageSize);
		List<User> list = userDao.selects(user);
		
		return new PageInfo<User>(list);
	}
	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}
	@Override
	public int insert(User user) {
		
		//int i=10/0;
		//后台注册校验规则，自定义
		//1.用户名不能为空
		if(!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空！");
		if(!(user.getUsername().length()>=2 && user.getUsername().length()<=10))
			throw new CMSException("用户名长度在2-10之间！");
		User findUser = this.selectByUsername(user.getUsername());
		if (null != findUser)
			throw new CMSException("用户名已经被注册.");
		//2.密码校验
		if(!StringUtil.hasText(user.getPassword()))
			throw new CMSException("密码不能为空！");
		if(!(user.getPassword().length()>=6 && user.getPassword().length()<=10))
			throw new CMSException("密码长度在6-10之间！");
		//3.确认密码
		if(!StringUtil.hasText(user.getRepassword()))
			throw new CMSException("确认密码不能为空！");
		if(!user.getRepassword().equals(user.getPassword()))
			throw new CMSException("两次密码输入不一致！");
		//4.对用户密码进行加密处理
		user.setPassword(Md5Util.encode(user.getPassword()));
		// 初始用户的注册信息----
		user.setCreated(new Date());//注册时间
		user.setNickname(user.getUsername());//默认用户昵称为用户名称
		user.setLocked("0");//默认用户状态是可用的
		
		return userDao.insert(user);
	}
	@Override
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.selectByUsername(username);
	}

	public User login(User user){
		//1.校验用户名是否为空
		if(!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空！");
		//2.检查用户名是否存在
		User u = this.selectByUsername(user.getUsername());
		if(null==u){
			throw new CMSException("该用户名不存在！");
		}
		// 3 比较密码是否一致 //数据库存储的是 加密后的密码
		// 对登录的密码再进行加密 再和数据库的密码进行比较
		if(!Md5Util.encode(user.getPassword()).equals(u.getPassword()))
			throw new CMSException("密码不正确，请重新输入！");
		
		if(u.getLocked().equals("1"))
			throw new CMSException("当前用户被禁用，不能登录！");
		//u是数据库查出来的
		return u;
	}
	
}
