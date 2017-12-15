package com.example.entity.announce;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.entity.Category;
import com.example.entity.Picture;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="secondhand")
public class SecondHand extends Announce{
	
	// 用mqppedBy,避免中间表的产生
	@OneToMany(mappedBy = "secondHand", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonManagedReference // 避免json 的无限循环
	private List<Picture> pictures;

	@ManyToOne
	@JsonBackReference // 避免json 的无限循环
	private Category category;
	
	private double prix;

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}
