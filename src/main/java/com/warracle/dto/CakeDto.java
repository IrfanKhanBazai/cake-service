package com.warracle.dto;


public class CakeDto {
	
	private String name;
    private String description;
    private String image;
	
    public CakeDto () {
    	
    }
    
    public CakeDto (String title, String description, String image) {
		this.name = title;
		this.description = description;
		this.image = image;
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
