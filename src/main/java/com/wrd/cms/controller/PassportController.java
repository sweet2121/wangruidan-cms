package com.wrd.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wrd.cms.domain.User;
import com.wrd.cms.service.UserService;
import com.wrd.cms.util.CMSException;
import com.wrd.cms.util.Result;

@RequestMapping("passport")
@Controller
public class PassportController {

	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 去註冊
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg(){
		
		return "passport/reg";
	}
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 执行注册
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@PostMapping("reg")
	@ResponseBody
	public Result<User> reg(User user,Model model){
		Result<User> result = new Result<User>();
		try {
			if(userService.insert(user)>0){
				result.setCode(200);
				result.setMsg("注册成功！");
			}
		} catch (CMSException e) {//自定义异常
			result.setCode(300);
			result.setMsg("注册失败！"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();//把异常消息控制台输出，方便找错误
			result.setCode(500);
			result.setMsg("注册失败，系统出现不可预知异常，请联系管理员！");
		}
		return result;
	}
	/**
	 * 
	 * @Title: login 
	 * @Description:去登录-普通用户
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login(){
		
		return "passport/login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description:去登录-管理员用户
	 * @return
	 * @return: String
	 */
	@GetMapping("admin/login")
	public String adminLogin(){
		
		return "passport/adminLogin";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 执行登录
	 * @param formUser
	 * @param model
	 * @param session
	 * @return
	 * @return: Result<User>
	 */
	@PostMapping("login")
	@ResponseBody
	public Result<User> login(User formUser,Model model,HttpSession session){
		Result<User> result = new Result<User>();

		try {
			//去登录。如果成功返回用户的基本信息
			User user = userService.login(formUser);
			//判断是否有该用户
			if(null!=user){
				result.setCode(200);
				result.setMsg("登录成功！");
				if(user.getRole()==0){
					session.setAttribute("user",user);
				}else{
					//登录成功，把用户信息存入session
					session.setAttribute("admin",user);
				}
			}
			
			
		} catch (CMSException e) {//自定义异常
			result.setCode(300);
			result.setMsg("登录失败！"+e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();//把异常消息控制台输出，方便找错误
			result.setCode(500);
			result.setMsg("登录失败，系统出现不可预知异常，请联系管理员！");
		}
		return result;
	}
	
	
	
	
	//注销用户
	@GetMapping("logout")
	public String  logout(HttpSession session) {
		
		//清除session
		session.invalidate();
		return "redirect:/";//回到系统首页
	}
	
	//检查注册用户是否存在
	@ResponseBody
	@PostMapping("checkName")
	public boolean checkName(String username) {
		
		return userService.selectByUsername(username) == null;
		
	}
	
}
