package com.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/** Persistence Domain to hold Credit card attributes */

@Entity
@Data
@Table(name = "CREDIT_CARD_DATA")
public class CreditCardEntity implements Serializable {

	private static final long serialVersionUID = 10004545454545L;

	@Id
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "CARD_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false)
	@Size(max = 255)
	private String name;

	@Column(name = "CARD_NUMBER", unique = true, nullable = false)
	@Size(max = 255)
	private String cardNumber;

	@Column(name = "CARD_BALANCE")
	private Double balance;

	@Column(name = "CARD_LIMIT")
	private Double limit;

	public CreditCardEntity() {
		// No Arg Constructor
	}
}
