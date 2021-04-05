package com.repository;

import com.domain.CreditCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardEntity, Long> {

	Optional<CreditCardEntity> findByCardNumber(String cardNumber);
}
