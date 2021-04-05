package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/** POJO to hold and transfer Credit card value between Service and UI with not null JSR validations */

@Component
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardDTO{

	/** Other JSR validations can also be introduced at field level like size and type */
	
	@NotNull
	private String name;

	@NotNull(message = "Credit card number can not be null")
	private String cardNumber;

	@NotNull(message = "Balance can not be null")
	private Double balance;

	@NotNull(message = "Limit can not be null")
	private Double limit;

}
