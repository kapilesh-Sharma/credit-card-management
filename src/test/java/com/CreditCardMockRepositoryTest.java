package com;

import com.domain.CreditCardEntity;
import com.repository.CreditCardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CreditCardMockRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CreditCardRepository CardRepository;

    @Test
    public void testFindAllCards() {
        Iterable<CreditCardEntity> cardEntities = CardRepository.findAll();
        Assert.assertTrue(Objects.nonNull(cardEntities));
        log.info("Successfully run Mock testFindAllCards() Cards");
    }

    @Test
    public void testSaveCards() {
        CreditCardEntity creditCardEntity = CardRepository.save(new CreditCardEntity());
        Assert.assertTrue(Objects.nonNull(creditCardEntity));
        log.info("Successfully run Mock testSaveCards() Cards");
    }
}
