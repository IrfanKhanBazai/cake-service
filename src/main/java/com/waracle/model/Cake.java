package com.waracle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Cake", uniqueConstraints = {@UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "NAME")})
public class Cake implements Serializable{
	
	private static final long serialVersionUID = 3435850896276707219L;	
 	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
    private Long id;
   

	@Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;
	
	@Column(name = "DESCRIPTION", unique = false, nullable = false, length = 100)
    private String description;
    
	@Column(name = "IMAGE", unique = false, nullable = false, length = 300)
    private String image; 
  
	public Cake (String title, String description, String image) {
		this.name = title;
		this.description = description;
		this.image = image;
	}
	
	public Cake() {		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
