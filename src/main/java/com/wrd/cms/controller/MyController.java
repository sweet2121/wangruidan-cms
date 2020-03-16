package com.wrd.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wrd.cms.domain.Article;
import com.wrd.cms.domain.Collect;
import com.wrd.cms.domain.ContentType;
import com.wrd.cms.domain.User;
import com.wrd.cms.service.ArticleService;
import com.wrd.cms.service.CollectService;

/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: 瑞
 * @date: 2020年3月4日 下午2:46:56
 */
@RequestMapping("my")
@Controller
public class MyController {

	@Resource
	private ArticleService service;
	@Resource
	private CollectService collectService;
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入个人中心的首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value={"","/","index"})
	public String index(){
		
		return "my/index";
	}
	
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 文章内容
	 * @param id
	 * @return
	 * @return: Article
	 */
	@ResponseBody
	@RequestMapping("articleDetail")
	public Article articleDetail(Integer id){
		
		
		return service.select(id);
	}
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 我的文章
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,HttpSession session,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize){
		Article article=new Article();
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//只显示当前登录人的文章
		PageInfo<Article> info=service.list(article, page, pageSize);
		
		model.addAttribute("info", info);
		return "my/articles";
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 去发布文章
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish(){
		
		return "my/publish";
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 发布文章
	 * @param file
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(MultipartFile file,Article article,HttpSession session) throws Exception{
		
		//文件上传
		if(null!=file && !file.isEmpty()){
			String path="d:/pic/";
			//文件的原始名字  1.jpg
			String filename = file.getOriginalFilename();
			//为了防止文件重名，需要改变文件的名字
			String newfilename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
			
			File f=new File(path, newfilename);
			//把文件写入d盘
			file.transferTo(f);
			article.setPicture(newfilename);//文件名称
		}
		//文章初始数据
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//只显示当前登录人的文章
		article.setCreated(new Date());
		article.setHits(0);//点击量默认 0
		article.setDeleted(0);//默认未删除
		article.setHot(0);//默认非热门
		article.setStatus(0);//默认待审核
		article.setContentType(ContentType.HTML);//文章类型
		//增加文章
		return service.insert(article)>0;
	}
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 我的收藏
	 * @return
	 * @return: String
	 */
	@RequestMapping("collects")
	public String collects(Model model,HttpSession session,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize){
		
		User user = (User) session.getAttribute("user");
		
		PageInfo<Collect> info=collectService.list(user, page, pageSize);
		
		model.addAttribute("info", info);
		
		return "my/collects";
	}
	/**
	 * 
	 * @Title: publicVote 
	 * @Description: 去发起投票
	 * @return
	 * @return: String
	 */
	@GetMapping("publishVote")
	public String publicVote(){
		
		return "/my/vote";
	}
	/**
	 * 
	 * @Title: publishVote
	 * @Description: 发起投票
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publishVote")
	public boolean publishVote(String[] options, Article article, HttpSession session) {
		// LinkedHashMap 是有顺序的，
		LinkedHashMap<Character, String> map = new LinkedHashMap<Character, String>();
		char x = 'A';// 选项从 ABCD等依次排序进行
		for (String option : options) {
			map.put(x, option);
			x = (char) (x + 1);
		}
		// 把map数据转为json
		Gson gson = new Gson();
		String json = gson.toJson(map);

		article.setContent(json);// 把json数据存入内容字段
		article.setContentType(ContentType.VOTE);//投票类型

		// 文章初始数据
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 发布人
		article.setCreated(new Date());
		article.setHits(0);// 点击量默认 0
		article.setDeleted(0);// 默认未删除
		article.setHot(0);// 默认非热门
		article.setStatus(1);// 默认已审核  ----  主要是测试 数据
		
		return service.insert(article) >0;

	}
}
