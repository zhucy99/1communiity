package com.example.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.entity.announce.Announce;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="comment")
public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	private String content;
	
	@ManyToOne
    @JsonBackReference // 避免json 的无限循环
    @JoinColumn(name = "announce_id")
    private Announce announce;
	
	@ManyToOne
    @JoinColumn(name = "author_id")
	@JsonManagedReference// 避免json 的无限循环
	private SysUser author;
	
	@Column(name = "create_time")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Announce getAnnounce() {
		return announce;
	}

	public void setAnnounce(Announce announce) {
		this.announce = announce;
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
	
}
