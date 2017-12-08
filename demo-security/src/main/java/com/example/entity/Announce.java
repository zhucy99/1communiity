package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
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
	// 用mqppedBy,避免中间表的产生
	@OneToMany(mappedBy = "announce", cascade = { CascadeType.REFRESH })
	private List<Picture> pictures;

	private int type;
	private int status;

	@ManyToOne
	private SysUser author;
	private Date createTime;
	private Date lastEditTime;

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

}
