package com.example.entity.announce;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.entity.Comment;
import com.example.entity.Picture;
import com.example.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@OnDelete(action = OnDeleteAction.CASCADE)
public class Announce {

	public static final int TYPE_SECONDHAND = 1;
	public static final int TYPE_SHARE = 2;
	public static final int TYPE_QUESTION = 3;

	public static final int STATUS_CREATE = 1;
	public static final int STATUS_PUBLISHED = 2;
	public static final int STATUS_END = 3;
	public static final int STATUS_CANCEL = 0;

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String description;

	private int type;
	@Transient
	private String typeStr;

	private int status;
	@Transient
	private String statusStr;

	@ManyToOne
	@JsonManagedReference // 避免json 的无限循环
	private SysUser author;
	private Date createTime;

	@Transient
	private String createTimeStr;
	private Date lastEditTime;
	
	@ManyToOne
	@JsonBackReference // 避免json 的无限循环
	private Category category;

	// 用mqppedBy,避免中间表的产生
	@OneToMany(mappedBy = "author", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JsonManagedReference // 避免json 的无限循环
	private List<Comment> comments;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public SysUser getAuthor() {
		return author;
	}

	public void setAuthor(SysUser author) {
		this.author = author;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

}
