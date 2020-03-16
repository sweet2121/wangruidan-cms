package com.wrd.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrd.cms.dao.ArticleDao;
import com.wrd.cms.domain.Article;
import com.wrd.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleDao dao;
	
	@Override
	public int insert(Article article) {
		// TODO Auto-generated method stub
		return dao.insert(article);
	}

	@Override
	public PageInfo<Article> list(Article article,Integer page, Integer pgeSize) {
		
		PageHelper.startPage(page, pgeSize);
		List<Article> list=dao.list(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public Article select(Integer id) {
		// TODO Auto-generated method stub
		return dao.select(id);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return dao.update(article);
	}

}
