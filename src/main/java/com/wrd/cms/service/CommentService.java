package com.wrd.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wrd.cms.domain.Article;
import com.wrd.cms.domain.Comment;

public interface CommentService {

	//增加评论
	int insert(Comment comment);
	//根据文章线程文章评论
	PageInfo<Comment> selects(Article article,Integer page,Integer pageSize);
	// 根据文章查询文章评论
	PageInfo<Article> selectsByCommentNum(Integer page,Integer pageSize);;
}
