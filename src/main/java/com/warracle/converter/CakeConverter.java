package com.warracle.converter;

import com.waracle.model.Cake;
import com.warracle.dto.CakeDto;

public class CakeConverter {
	
	public static Cake dtoToEntity(CakeDto cakeDto) {
		Cake cake = new Cake();
		cake.setName(cakeDto.getName());
		cake.setDescription(cakeDto.getDescription());
		cake.setImage(cakeDto.getImage());		
		return cake;
	}
	
	public static CakeDto entityToDto(Cake cake) {
		CakeDto cakeDto = new CakeDto();
		cakeDto.setName(cake.getName());
		cakeDto.setDescription(cake.getDescription());
		cakeDto.setImage(cake.getImage());		
		return cakeDto;
	}

}
