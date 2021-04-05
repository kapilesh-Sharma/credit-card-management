package com.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.domain.CreditCardEntity;
import com.dto.CreditCardDTO;

@Component
public class CardUtility {

	@Autowired
	ModelMapper modelMapper;

	public CreditCardDTO convertToCreditCardDTO(CreditCardEntity creditCardEntity) {
		return modelMapper.map(creditCardEntity, CreditCardDTO.class);
	}

	public CreditCardEntity convertToCreditCardEntity(CreditCardDTO creditCardDTO) {
		return modelMapper.map(creditCardDTO, CreditCardEntity.class);
	}
}
