package com.services;

import com.domain.CreditCardEntity;
import com.exception.CreditCardException;
import com.repository.CreditCardRepository;
import com.util.CardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    CreditCardRepository cardRepository;

    @Autowired
    CardValidator cardValidator;

    @Transactional(readOnly = true)
    @Override
    public Iterable<CreditCardEntity> getAllCards() {
        return cardRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveCard(final CreditCardEntity cardEntity) throws CreditCardException {
        cardValidator.cardInputValidation(cardEntity);
        final Optional<CreditCardEntity> creditCardRecord = cardRepository.findByCardNumber(cardEntity.getCardNumber());
        if (creditCardRecord.isPresent()) {
            throw new CreditCardException(CreditCardException.DUPLICATE_CARD_NUMBER);
        }
        cardRepository.save(cardEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CreditCardEntity updateCard(final String cardNumber, final CreditCardEntity creditCardRequest) throws CreditCardException {
        cardValidator.cardInputValidation(creditCardRequest);
        return cardRepository.findByCardNumber(cardNumber)
                .map(creditCard -> {
                    creditCard.setName(creditCardRequest.getName());
                    creditCard.setBalance(creditCardRequest.getBalance());
                    creditCard.setLimit(creditCardRequest.getLimit());
                    return cardRepository.save(creditCard);
                })
                .orElseGet(() -> {
                    return cardRepository.save(creditCardRequest);
                });
    }
}
