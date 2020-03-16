package com.wrd.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @ClassName: UserInterceptor 
 * @Description: 个人中心 拦截器
 * @author: 瑞
 * @date: 2020年3月13日 上午8:39:51
 */
public class MyInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//如果用户已经登录，不拦截
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if(null!=obj)
			return true;//不拦截
		//重定向
		//response.sendRedirect("/passport/login");
		request.setAttribute("msg", "请登录后重试");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		return false;//拦截
	}
}
