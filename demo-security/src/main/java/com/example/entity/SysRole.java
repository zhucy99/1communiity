package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class SysRole {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    
    @Transient
    private String descriptionTrad;
    
    @Transient
    private String nameTrad;


    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public String getDescriptionTrad() {
		return descriptionTrad;
	}

	public void setDescriptionTrad(String descriptionTrad) {
		this.descriptionTrad = descriptionTrad;
	}

	public String getNameTrad() {
		return nameTrad;
	}

	public void setNameTrad(String nameTrad) {
		this.nameTrad = nameTrad;
	}
    
}
