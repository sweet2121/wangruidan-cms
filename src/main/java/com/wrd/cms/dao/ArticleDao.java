package com.wrd.cms.dao;

import java.util.List;

import com.wrd.cms.domain.Article;

public interface ArticleDao {

	//添加文章
	int insert(Article article);
	//查询文章
	List<Article> list(Article article);
	//单个文章
	Article select(Integer id);
	//修改热门文章
	int update(Article article);
	
}
