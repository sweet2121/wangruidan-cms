package com.wrd.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrd.cms.dao.CommentDao;
import com.wrd.cms.domain.Article;
import com.wrd.cms.domain.Comment;
import com.wrd.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentDao dao;
	//增加评论
	@Override
	public int insert(Comment comment) {
		try {
			//增加评论
			dao.insert(comment);
			//让文章的评论数据+1
			dao.updateArticle(comment.getArticleId());
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public PageInfo<Comment> selects(Article article, Integer page, Integer pageSize) {
		
		PageHelper.startPage(page, pageSize);
		List<Comment> list = dao.selects(article);
		return new PageInfo<Comment>(list);
	}

	@Override
	public PageInfo<Article> selectsByCommentNum(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> list=dao.selectsByCommentNum();
		return new PageInfo<Article>(list);
	}

}
