package com.wrd.cms.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wrd.cms.domain.Article;

public interface ArticleService {

	//添加文章
	int insert(Article article);
	//查询文章
	PageInfo<Article> list(Article article, Integer page, Integer pgeSize);
	//单个文章
	Article select(Integer id);
	
	int update(Article article);
	
}
