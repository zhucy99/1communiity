package com.example.entity.announce;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private Long id;
	private String code;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="parent_id", nullable=true)
    private Category parent;
	
    @OneToMany(mappedBy="parent",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Category> children;
	
	@OneToMany(mappedBy="category",cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JsonManagedReference// 避免json 的无限循环
	private List<Announce> announces;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public List<Category> getChildren() {
		return children;
	}
	public void setChildren(List<Category> children) {
		this.children = children;
	}
	public List<Announce> getAnnounces() {
		return announces;
	}
	public void setAnnounces(List<Announce> announces) {
		this.announces = announces;
	}
	
}
