package com.integration;

import com.dto.CreditCardDTO;
import com.exception.ExceptionDetails;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCardControllerIntegrationTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String REST_ENDPOINT = "/api/v1/credit-cards";
    public static HttpHeaders httpHeaders;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * -- Positive Test scenario to test the Card CRUD functionality --
     */

    @BeforeClass
    public static void prepareSecurityHeaders() {
        httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Basic dXNlcjpwYXNzd29yZA==");
    }

    @Test
    public void testGetAllCards() {
        HttpEntity<CreditCardDTO> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<CreditCardDTO[]> responseEntity = restTemplate.exchange(REST_ENDPOINT, HttpMethod.GET, entity, CreditCardDTO[].class);
        List<CreditCardDTO> creditCards = Arrays.asList(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Kapilesh", creditCards.get(0).getName());
        log.info("testGetAllCards completed successfully with response  -" + creditCards.get(0).getName());
    }

    @Test
    public void testCreateNewCard() {

        CreditCardDTO creditCardDTO = prepareValidTestData();
        creditCardDTO.setCardNumber("340000000000009");
        HttpEntity<CreditCardDTO> entity = new HttpEntity<CreditCardDTO>(creditCardDTO, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(REST_ENDPOINT, HttpMethod.POST, entity,
                String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
        log.info("testCreateNewCard completed successfully with response -" + response.getStatusCode());
    }

    @Test
    public void testUpdateCard() {

        CreditCardDTO creditCardDTO = prepareValidTestData();
        creditCardDTO.setLimit(212.0);

        HttpEntity<CreditCardDTO> entity = new HttpEntity<CreditCardDTO>(creditCardDTO, httpHeaders);
        ResponseEntity<CreditCardDTO> response = restTemplate.exchange(REST_ENDPOINT + "/" + "4111111111111111", HttpMethod.PUT, entity,
                CreditCardDTO.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        log.info("testUpdateCard completed successfully with response -" + response.getStatusCode());
    }

    /**
     * -- Negative Test scenario --
     */

    @Test
    public void testCreateNewCardWithCreditCardException() throws Exception {

        HttpEntity<CreditCardDTO> entity = new HttpEntity<CreditCardDTO>(prepareInvalidTestData(), httpHeaders);
        ResponseEntity<ExceptionDetails> response = restTemplate.exchange(REST_ENDPOINT, HttpMethod.POST, entity,
                ExceptionDetails.class);
        log.info("testCreateNewCardWithCreditCardException completed successfully details -" + response);
    }

    @Test
    public void testCreateNewCardWithNullException() throws Exception {

        HttpEntity<CreditCardDTO> entity = new HttpEntity<CreditCardDTO>(prepareEmptyTestData(), httpHeaders);
        ResponseEntity<ExceptionDetails> response = restTemplate.exchange(REST_ENDPOINT + "/ll", HttpMethod.POST, entity,
                ExceptionDetails.class);
        log.info("testCreateNewCardWithNullException completed successfully details -" + response);
    }

    static CreditCardDTO prepareValidTestData() {
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setName("Test Card");
        creditCardDTO.setBalance(0.0);
        creditCardDTO.setLimit(1000.0);
        creditCardDTO.setCardNumber("4111111111111111");
        return creditCardDTO;
    }

    static CreditCardDTO prepareEmptyTestData() {
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setName(null);
        creditCardDTO.setBalance(0.0);
        creditCardDTO.setLimit(1000.0);
        creditCardDTO.setCardNumber(null);
        return creditCardDTO;
    }

    static CreditCardDTO prepareInvalidTestData() {
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setName("Test Card");
        creditCardDTO.setBalance(100.0);
        creditCardDTO.setLimit(1000.0);
        creditCardDTO.setCardNumber("411");
        return creditCardDTO;
    }
}
