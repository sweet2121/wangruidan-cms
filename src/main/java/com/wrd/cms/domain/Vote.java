package com.wrd.cms.domain;

import java.math.BigDecimal;

public class Vote {

	private Integer id;
	private Integer articleId;
	private Integer userId;
	
	private String option;//投票选项 
	private Integer optionNum;//单票票数
	private Integer totalNum;//投票总票数
	private BigDecimal percent;//单票占百分比
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Integer getOptionNum() {
		return optionNum;
	}
	public void setOptionNum(Integer optionNum) {
		this.optionNum = optionNum;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public BigDecimal getPercent() {
		return percent;
	}
	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "Vote [id=" + id + ", articleId=" + articleId + ", userId=" + userId + ", option=" + option
				+ ", optionNum=" + optionNum + ", totalNum=" + totalNum + ", percent=" + percent + "]";
	}
	public Vote(Integer id, Integer articleId, Integer userId, String option, Integer optionNum, Integer totalNum,
			BigDecimal percent) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.userId = userId;
		this.option = option;
		this.optionNum = optionNum;
		this.totalNum = totalNum;
		this.percent = percent;
	}
	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
