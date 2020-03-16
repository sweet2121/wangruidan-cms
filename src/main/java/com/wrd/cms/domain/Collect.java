package com.wrd.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class Collect implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String text;//文章标题
	private String url;//文章的
	private User user;//收藏人
	private Date created;//收藏时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Collect [id=" + id + ", text=" + text + ", url=" + url + ", user=" + user + ", created=" + created + "]";
	}
	public Collect(Integer id, String text, String url, User user, Date created) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.user = user;
		this.created = created;
	}
	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
