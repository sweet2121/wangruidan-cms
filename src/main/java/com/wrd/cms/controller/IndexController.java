package com.wrd.cms.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wrd.cms.domain.Article;
import com.wrd.cms.domain.Category;
import com.wrd.cms.domain.Channel;
import com.wrd.cms.domain.ChooseResult;
import com.wrd.cms.domain.Collect;
import com.wrd.cms.domain.Comment;
import com.wrd.cms.domain.ContentType;
import com.wrd.cms.domain.Slide;
import com.wrd.cms.domain.User;
import com.wrd.cms.domain.Vote;
import com.wrd.cms.service.ArticleService;
import com.wrd.cms.service.ChannelService;
import com.wrd.cms.service.ChooseResultService;
import com.wrd.cms.service.CollectService;
import com.wrd.cms.service.CommentService;
import com.wrd.cms.service.SlideService;
import com.wrd.cms.service.VoteService;
import com.wrd.common.utils.NumberUtil;
/**
 * 
 * @ClassName: IndexController 
 * @Description: 系统首页入口
 * @author: 瑞
 * @date: 2020年3月9日 下午7:15:20
 */
@Controller
public class IndexController {

	@Resource
	private ChannelService service;
	@Resource
	private ArticleService articleService;
	@Resource
	private SlideService slideService;
	@Resource
	private CommentService commentService;
	@Resource
	private CollectService collectService;
	@Resource
	private VoteService voteService;
	@Resource
	private ChooseResultService chooseResultService;
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入首页
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value={"","/","index"})
	public String index(Model model,Article article,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pageSize){
		article.setStatus(1);//显示审核过的文章
		article.setDeleted(0);
		//封装查询条件
		model.addAttribute("article", article);
		
		//查询左侧栏目
		List<Channel> channels = service.list();
		model.addAttribute("channels", channels);
		
		//如果栏目不为空，则查询下面的分类
		if(article.getChannelId()!=null){
			List<Category> categorys = service.selects(article.getChannelId());
			model.addAttribute("categorys", categorys);
		}
		
		//如果栏目为空，说明没有点击左侧栏目，默认热点文章
		if(article.getChannelId()==null){
			article.setHot(1);
			List<Slide> slides=slideService.selects();
			model.addAttribute("slides", slides);
		}
		//查询所有的文章
		PageInfo<Article> info = articleService.list(article, page, pageSize);
		model.addAttribute("info", info);
		
		//右侧显示10篇普通文章 new Article()
		Article article2=new Article();
		article2.setStatus(1);//显示审核过的文章
		article2.setDeleted(0);
		PageInfo<Article> lastArticles = articleService.list(article2,1,5);
		model.addAttribute("lastArticles",lastArticles);
		
		//问卷调查
		Article voteArticle = new Article();
		voteArticle.setStatus(1);
		voteArticle.setDeleted(0);
		voteArticle.setContentType(ContentType.VOTE);
		
		PageInfo<Article> voteArticles = articleService.list(voteArticle, 1, 10);
		model.addAttribute("voteArticles", voteArticles);
		
		return "index/index";
	}
	
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description:文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("articleDetail")
	public String articleDetail(HttpSession session,Integer id,Model model,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize){
		
		Article article = articleService.select(id);
		model.addAttribute("article", article);
		
		// 查询出当前文章的评论信息
		PageInfo<Comment> info = commentService.selects(article, page, pageSize);
		// 查询所有文章的评论
		PageInfo<Article> info2 = commentService.selectsByCommentNum(1, 10);
				
		model.addAttribute("info", info);
		model.addAttribute("info2", info2);
		//查询该文章是否被收藏过
		User user = (User) session.getAttribute("user");
		//如果用户已经登录，则查询是否被收藏过
		Collect collect = null;
		if(null!=user){
			collect = collectService.selectByTitleAndUserId(article.getTitle(), user.getId());
		}
		model.addAttribute("collect", collect);
		
		return "index/articleDetail";
	}
	
	/**
	 * 
	 * @Title: addComment 
	 * @Description: 增加评论
	 * @param comment
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("addComment")
	public boolean addComment(Comment comment,Integer articleId,HttpSession session){
		
		User user=(User) session.getAttribute("user");
		if(null==user)
			return false;//没有登录的用户不能评论
		
		comment.setUserId(user.getId());
		comment.setArticleId(articleId);
		comment.setCreated(new Date());
		return commentService.insert(comment)>0;
	}
	/**
	 * 
	 * @Title: collect 
	 * @Description:收藏文章
	 * @param collect
	 * @param session
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("collect")
	public boolean collect(Collect collect,HttpSession session){
		User user=(User) session.getAttribute("user");
		if(null==user)
			return false;
		collect.setUser(user);
		collect.setCreated(new Date());
		
		return collectService.insert(collect)>0;
	}
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 取消收藏文章
	 * @param id
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("deleteCollect")
	public boolean deleteCollect(Integer id) {
		return collectService.delete(id) >0;
	}
	/**
	 * 
	 * @Title: voteDetail 
	 * @Description: 投票详情
	 * @param session
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("voteDetail")
	public String voteDetail(HttpSession session,Integer id,Model model){
		Article article = articleService.select(id);
		String content = article.getContent();
		Gson gson =new Gson();
		LinkedHashMap<Character,String> mapVote = gson.fromJson(content, LinkedHashMap.class);
	
		
		model.addAttribute("mapVote", mapVote);
		model.addAttribute("article", article);
		
		//查询投票情况
		List<Vote> votes = voteService.selects(article.getId());
		for (Vote vote : votes) {
			//获取选项的值并重新封装到vote
			vote.setOption(mapVote.get(vote.getOption()));
			//======计算百分比
			vote.setPercent(new BigDecimal(NumberUtil.getPercent(vote.getOptionNum(),vote.getTotalNum())));
		}
		model.addAttribute("votes", votes);
		
		return "index/voteDetail";
	}
	
	//投票
	@ResponseBody
	@PostMapping("addVote")
	public boolean addVote(Vote vote ,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null ==user)
		return false;//没有登录的用户不能收藏
		vote.setUserId(user.getId());
		//检查用户是否已经投过票
		if(voteService.select(vote)!=null)
			return false;
	
		return voteService.insert(vote) >0;
	}
	/**
	 * 
	 * @Title: voteDetail 
	 * @Description: 去投票
	 * @param session
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("chooseDetail")
	public String chooseDetail(HttpSession session,Integer id,Model model){
		Article article = articleService.select(id);
		String content = article.getContent();
		Gson gson =new Gson();
		LinkedHashMap<Character,String> mapChoose = gson.fromJson(content, LinkedHashMap.class);
	
		
		model.addAttribute("mapChoose", mapChoose);
		model.addAttribute("article", article);
		
		//查询投票情况
		List<ChooseResult> choose = chooseResultService.selects(article.getId());
		for (ChooseResult vote : choose) {
			//获取选项的值并重新封装到vote
			vote.setOption(mapChoose.get(vote.getOption()));
			//======计算百分比
			vote.setPercent(new BigDecimal(NumberUtil.getPercent(vote.getOptionNum(),vote.getTotalNum())));
		}
		model.addAttribute("choose", choose);
		
		return "index/chooseDetail";
	}
	
	//投票
	@ResponseBody
	@PostMapping("addChoose")
	public boolean addVote(ChooseResult chooseResult ,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null ==user)
		return false;//没有登录的用户不能收藏
		chooseResult.setUserId(user.getId());
		//检查用户是否已经投过票
		if(chooseResultService.select(chooseResult)!=null)
			return false;
	
		return chooseResultService.insert(chooseResult) >0;
	}
}

