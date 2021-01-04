package com.challenge.voting.resource;


import com.challenge.voting.model.Agenda;
import com.challenge.voting.model.Answer;
import com.challenge.voting.model.Session;
import com.challenge.voting.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureDataMongo
public class AgendaResourceTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void saveAgenda() {
        webTestClient.post()
                .uri("/agendas")
                .body(BodyInserters.fromValue(new Agenda("Agenda Test")))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void findByIdNotFound() {
        webTestClient.get()
                .uri("agendas/13123123")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void findById() {
        Agenda agenda = webTestClient.post()
                .uri("/agendas")
                .body(BodyInserters.fromValue(new Agenda("Agenda Test")))
                .exchange()
                .returnResult(Agenda.class)
                .getResponseBody()
                .next()
                .block();

        webTestClient.get()
                .uri("agendas/" + agenda.getId())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void findAllEmpty() {
        boolean isEmpty = webTestClient.get()
                .uri("agendas")
                .exchange()
                .returnResult(Agenda.class)
                .getResponseBody()
                .collectList()
                .block()
                .isEmpty();

        assertTrue(isEmpty);
    }

    @Test
    public void findAllNotEmpty() {
        webTestClient.post()
                .uri("/agendas")
                .body(BodyInserters.fromValue(new Agenda("Agenda Test")))
                .exchange()
                .expectStatus()
                .isOk();

        boolean isEmpty = webTestClient.get()
                .uri("agendas")
                .exchange()
                .returnResult(Agenda.class)
                .getResponseBody()
                .collectList()
                .block()
                .isEmpty();

        assertFalse(isEmpty);
    }

    @Test
    public void voteCount() {
        Agenda agenda = webTestClient.post()
                .uri("/agendas")
                .body(BodyInserters.fromValue(new Agenda("Agenda Test")))
                .exchange()
                .returnResult(Agenda.class)
                .getResponseBody()
                .next()
                .block();

        Session session = webTestClient.post()
                .uri("/agendas/" + agenda.getId() + "/sessions")
                .body(BodyInserters.fromValue(new Session(1L, agenda)))
                .exchange()
                .returnResult(Session.class)
                .getResponseBody()
                .next()
                .block();

        webTestClient.post()
                .uri("sessions/" + session.getId() + "/votes")
                .body(BodyInserters.fromValue(new Vote("123123123", Answer.YES, session)))
                .exchange()
                .expectStatus()
                .isOk();

        webTestClient.post()
                .uri("sessions/" + session.getId() + "/votes")
                .body(BodyInserters.fromValue(new Vote("123123124", Answer.YES, session)))
                .exchange()
                .expectStatus()
                .isOk();

        webTestClient.post()
                .uri("sessions/" + session.getId() + "/votes")
                .body(BodyInserters.fromValue(new Vote("123123125", Answer.NO, session)))
                .exchange()
                .expectStatus()
                .isOk();

        webTestClient.post()
                .uri("sessions/" + session.getId() + "/votes")
                .body(BodyInserters.fromValue(new Vote("123123126", Answer.NO, session)))
                .exchange()
                .expectStatus()
                .isOk();

        Agenda agenda1 = webTestClient.get()
                .uri("agendas/" + agenda.getId())
                .exchange()
                .returnResult(Agenda.class)
                .getResponseBody()
                .next()
                .block();

        assertEquals(agenda1.getNo(), 2L);
        assertEquals(agenda1.getYes(), 2L);
    }
}
