package com.challenge.voting.resource;


import com.challenge.voting.model.Agenda;
import com.challenge.voting.model.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureDataMongo
public class SessionResourceTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AgendaResource agendaResource;

    @Test
    public void save() {
        Agenda agenda = webTestClient.post()
                .uri("/agendas")
                .body(BodyInserters.fromValue(new Agenda("Agenda Test")))
                .exchange()
                .returnResult(Agenda.class)
                .getResponseBody()
                .next()
                .block();

        webTestClient.post()
                .uri("/agendas/" + agenda.getId() + "/sessions")
                .body(BodyInserters.fromValue(new Session(1L, agenda)))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void saveNotExists() {
        webTestClient.post()
                .uri("/agendas/123123/sessions")
                .body(BodyInserters.fromValue(new Session(1L, new Agenda())))
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}
