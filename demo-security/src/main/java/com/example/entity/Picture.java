package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.entity.announce.SecondHand;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Picture {
	
	@Id
	@GeneratedValue
	private Long id;
    private String title;
    private String description;
    
    @Column(name = "file_name")
    private String fileName;
    
    @ManyToOne
    @JsonBackReference // 避免json 的无限循环
    @JoinColumn(name = "second_hand_id")
    private SecondHand secondHand;
    
	public SecondHand getSecondHand() {
		return secondHand;
	}
	public void setSecondHand(SecondHand secondHand) {
		this.secondHand = secondHand;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
}
