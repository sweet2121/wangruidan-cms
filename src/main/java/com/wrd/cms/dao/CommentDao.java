package com.wrd.cms.dao;

import java.util.List;

import com.wrd.cms.domain.Article;
import com.wrd.cms.domain.Comment;

public interface CommentDao {

	//增加评论
	int insert(Comment comment);
	//根据文章线程文章评论
	List<Comment> selects(Article article);
	
	//评论数+1
	int updateArticle(Integer articleId);
	//按照评论数量排序
	List<Article> selectsByCommentNum();
	
}
