package com.wrd.cms.domain;

import java.math.BigDecimal;

public class ChooseResult {

	private Integer id;
	private Integer userId;
	private Integer articleId;
	private String option;
	
	private Integer optionNum;//单票票数
	private Integer totalNum;//投票总票数
	private BigDecimal percent;//单票占百分比
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
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
		return "ChooseResult [id=" + id + ", userId=" + userId + ", articleId=" + articleId + ", option=" + option
				+ ", optionNum=" + optionNum + ", totalNum=" + totalNum + ", percent=" + percent + "]";
	}
	public ChooseResult(Integer id, Integer userId, Integer articleId, String option, Integer optionNum,
			Integer totalNum, BigDecimal percent) {
		super();
		this.id = id;
		this.userId = userId;
		this.articleId = articleId;
		this.option = option;
		this.optionNum = optionNum;
		this.totalNum = totalNum;
		this.percent = percent;
	}
	public ChooseResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
