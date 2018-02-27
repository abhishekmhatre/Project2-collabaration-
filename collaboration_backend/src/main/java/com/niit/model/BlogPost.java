package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="blogpost_s180233")
public class BlogPost {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int id;
	private String blogTitle;
	@Lob
	private String blogContent;
	@ManyToOne
	private User postedOn;
	private Date postedBy;
	private int likes;
	private boolean approved;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public User getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(User postedOn) {
		this.postedOn = postedOn;
	}
	public Date getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(Date postedBy) {
		this.postedBy = postedBy;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public void setPostedOn(Date date) {
		// TODO Auto-generated method stub
		
	}
	public void setPostedBy(User postedBy2) {
		// TODO Auto-generated method stub
		
	}
	
}
