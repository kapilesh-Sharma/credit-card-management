package com.controller;

import com.domain.CreditCardEntity;
import com.dto.CreditCardDTO;
import com.exception.CreditCardException;
import com.services.CreditCardService;
import com.util.CardUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * CreditCard controller to perform CRUD operations
 * functionality
 */

@RestController
@RequestMapping(value = "api/v1/credit-cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditCardController {

    private final Logger log = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    CreditCardService cardService;

    @Autowired
    CardUtility cardUtility;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
        log.info("In getAllCreditCards");
        return new ResponseEntity<>(StreamSupport.stream(cardService.getAllCards().spliterator(), false).
                map(creditCard -> cardUtility.convertToCreditCardDTO(creditCard))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditCardDTO> addCreditCard(@Valid @RequestBody final CreditCardDTO creditCardDTO)
            throws CreditCardException {
        log.debug("In addCreditCard " + creditCardDTO);
        cardService.saveCard(cardUtility.convertToCreditCardEntity(creditCardDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cardNumber}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditCardDTO> updateCreditCard(@NotBlank @PathVariable final String cardNumber, @Valid @RequestBody final CreditCardDTO creditCardDTO)
            throws CreditCardException {
        log.debug("In updateCreditCard with input dto -" + creditCardDTO);
        final CreditCardEntity creditCardEntity = cardService.updateCard(cardNumber, cardUtility.convertToCreditCardEntity(creditCardDTO));
        return new ResponseEntity<>(cardUtility.convertToCreditCardDTO(creditCardEntity), HttpStatus.OK);
    }


}
