package com;

import com.domain.CreditCardEntity;
import com.exception.CreditCardException;
import com.repository.CreditCardRepository;
import com.services.CreditCardServiceImpl;
import com.util.CardValidator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardMockTestService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @InjectMocks
    CreditCardServiceImpl cardService;
    @Mock
    CreditCardRepository creditCardRepository;
    @Mock
    CardValidator cardValidator;

    static List<CreditCardEntity> cardList = new ArrayList<>();
	static CreditCardEntity cardEntity;

	@BeforeClass
    public static void prepareValidTestData() {
        cardEntity = new CreditCardEntity();
        cardEntity.setName("Test Card");
        cardEntity.setBalance(0.0);
        cardEntity.setLimit(1000.0);
        cardEntity.setCardNumber("4111111111111111");
        cardList.add(cardEntity);
    }

    @AfterClass
    public static void clear() {
        cardEntity = null;
        cardList = null;
    }

    @Test
    public void testFindAllCards() {
        //given
        when(creditCardRepository.findAll()).thenReturn(cardList);
        //when
        String cardHolder = cardService.getAllCards().iterator().next().getName();
        //then
        assertEquals("Test Card", cardHolder);
        log.info("Successfully run Mock findAll() Cards");
    }

	@Test
	public void testSaveCards() throws CreditCardException {
		//given
		when(creditCardRepository.save(any(CreditCardEntity.class))).thenReturn(new CreditCardEntity());
		//when
		cardService.saveCard(cardEntity);
		//then
		verify(creditCardRepository, times(1)).save(any(CreditCardEntity.class));
		log.info("Successfully run Mock saveCards() Cards");
	}

	@Test
	public void testUpdateCardsWithoutEntry() throws CreditCardException {
		//given
        doNothing().when(cardValidator).cardInputValidation(any(CreditCardEntity.class));
		when(creditCardRepository.save(any(CreditCardEntity.class))).thenReturn(new CreditCardEntity());
        //when
		cardService.updateCard("4111111111111111",cardEntity);
		//then
		verify(creditCardRepository, times(1)).save(any(CreditCardEntity.class));
		log.info("Successfully run Mock updateCards() with new record save");
	}

	@Test
	public void testUpdateCardsWithEntry() throws CreditCardException {
		//given
        doNothing().when(cardValidator).cardInputValidation(any(CreditCardEntity.class));
		when(creditCardRepository.save(any(CreditCardEntity.class))).thenReturn(new CreditCardEntity());
		when(creditCardRepository.findByCardNumber(anyString())).thenReturn(java.util.Optional.of(new CreditCardEntity()));
		//when
		cardService.updateCard("4111111111111111",cardEntity);
		//then
		verify(creditCardRepository, times(1)).save(any(CreditCardEntity.class));
		log.info("Successfully run Mock updateCards() Cards");
	}




    /** -- Other mocking test scenarios and Negative test cases -- */

    @Test(expected = CreditCardException.class)
    public void testSaveCardsWithDuplicateRecordError() throws CreditCardException {
        //given
        doNothing().when(cardValidator).cardInputValidation(any(CreditCardEntity.class));
        when(creditCardRepository.findByCardNumber(anyString())).thenReturn(java.util.Optional.of(new CreditCardEntity()));
        //when
        cardService.saveCard(cardEntity);
        //then
        //Expecting error
    }
}
