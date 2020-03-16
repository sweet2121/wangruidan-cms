package com.wrd.cms.service.impl;


import java.util.Date;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.wrd.cms.domain.Article;
import com.wrd.cms.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleServiceImplTest {

	@Resource
	private ArticleService service;
	
	@Test
	public void testInsert() {
		Article article=new Article();
		
		article.setTitle("test");
		article.setUserId(138);
		article.setChannelId(1);
		article.setCategoryId(1);
		article.setContent("飞规划局快乐");
		article.setCreated(new Date());
		
		service.insert(article);
		
	}

	@Test
	public void testList() {
		
		PageInfo<Article> list=service.list(null,1,10);
		System.out.println(list.getList());
	}

}
