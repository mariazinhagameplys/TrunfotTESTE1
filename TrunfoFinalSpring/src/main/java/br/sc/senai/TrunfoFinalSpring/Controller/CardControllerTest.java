package br.sc.senai.TrunfoFinalSpring.Controller;

import br.sc.senai.TrunfoFinalSpring.Model.DTO.CardDTO;
import br.sc.senai.TrunfoFinalSpring.Model.Entity.Card;
import br.sc.senai.TrunfoFinalSpring.Model.Enum.Emotion;
import br.sc.senai.TrunfoFinalSpring.Repository.CardRepository;
import br.sc.senai.TrunfoFinalSpring.Service.CardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @Test
    public void testCreate() {
        cardRepository.deleteAll();
        Card card1 = new Card(1l, "Card 1", "Description 1", 1,1, 1,1, 1, Emotion.SAD);
        Card card2 = new Card(2l, "Card 2", "Description 2", 2,2, 2,2, 2, Emotion.ANGRY);
        cardService.createUpdate(card1);
        cardService.createUpdate(card2);
        CardDTO cardDTO = new CardDTO("Card 3", "Description 3", 3,3, 3,3, 3, Emotion.HAPPY);;

        ResponseEntity<Card> response = restTemplate.postForEntity("/card/create", cardDTO, Card.class);
        Card card = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(card.getId());
        assertEquals(cardDTO.getName(), card.getName());
        assertEquals(cardDTO.getDescription(), card.getDescription());
    }

    @Test
    public void testSearchAll() {
        cardRepository.deleteAll();
        Card card4 = new Card(4l, "Card 4", "Description 1", 1,1, 1,1, 1, Emotion.SAD);
        Card card5 = new Card(5l, "Card 5", "Description 2", 2,2, 2,2, 2, Emotion.ANGRY);
        cardService.createUpdate(card4);
        cardService.createUpdate(card5);

        ResponseEntity<List> response = restTemplate.getForEntity("/card/getCards", List.class);
        List<Card> cards = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, cards.size());
    }

    @Test
    public void testDelete() {
        cardRepository.deleteAll();
        Card card6 = new Card(6l, "Card 1", "Description 1", 1,1, 1,1, 1, Emotion.SAD);
        cardService.createUpdate(card6);

        Long id = card6.getId();
        restTemplate.delete("/card/" + id);
        assertFalse(cardRepository.findById(id).isPresent());
    }

    @Test
    public void testSearchOne() {
        cardRepository.deleteAll();
        Card card7 = new Card(7l, "Card 1", "Description 1", 1,1, 1,1, 1, Emotion.SAD);
        cardService.createUpdate(card7);
        Long id = card7.getId();
        ResponseEntity<Card> response = restTemplate.getForEntity("/card/getCard/" + id, Card.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());


    }

    @Test
    public void testUpdate() {
        cardRepository.deleteAll();
        Card card8 = new Card(8l, "Card 1", "Description 1", 1,1, 1,1, 1, Emotion.SAD);
        cardService.createUpdate(card8);

        Long id = card8.getId();
        CardDTO updatedCardDTO = new CardDTO("Card 4", "Description 4", 4,4, 4,4, 4, Emotion.HAPPY);
        ResponseEntity<Card> response = restTemplate.exchange("/card/update/" + id, HttpMethod.PUT, new HttpEntity<>(updatedCardDTO), Card.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Card card = response.getBody();
        assertNotNull(card);
        assertEquals(id, card.getId());
        assertEquals(updatedCardDTO.getName(), card.getName());
        assertEquals(updatedCardDTO.getDescription(), card.getDescription());
    }
}
