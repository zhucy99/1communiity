package com.example.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.entity.announce.SecondHand;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	@OneToMany(mappedBy="category",cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JsonManagedReference// 避免json 的无限循环
	private List<SecondHand> SecondHand;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<SecondHand> getSecondHand() {
		return SecondHand;
	}
	public void setSecondHand(List<SecondHand> secondHand) {
		SecondHand = secondHand;
	}
	
}
