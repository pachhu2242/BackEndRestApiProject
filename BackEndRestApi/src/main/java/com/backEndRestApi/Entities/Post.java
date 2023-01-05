package com.backEndRestApi.Entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Posts")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postid;
	
	@Column(name="Post_Title", length=100, nullable=false)
	private String title;
	
	@Column(length = 10000)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name="category_Id")
	private Category category;
	
	@ManyToOne
	private User user;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Integer getPostid() {
		return postid;
	}



	public void setPostid(Integer postid) {
		this.postid = postid;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	


}
