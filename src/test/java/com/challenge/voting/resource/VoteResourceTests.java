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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureDataMongo
public class VoteResourceTests {

    @Autowired
    private WebTestClient webTestClient;

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

        Session session = webTestClient.post()
                .uri("/agendas/" + agenda.getId() + "/sessions")
                .body(BodyInserters.fromValue(new Session(1L, agenda)))
                .exchange()
                .returnResult(Session.class)
                .getResponseBody()
                .next()
                .block();

        webTestClient.post()
                .uri("sessions/"+session.getId()+"/votes")
                .body(BodyInserters.fromValue(new Vote("123123123", Answer.YES, session)))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void saveNotFound() {
        webTestClient.post()
                .uri("sessions/12313/votes")
                .body(BodyInserters.fromValue(new Vote("123123123", Answer.YES, new Session())))
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void hasAlreadyVoted() {
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
                .uri("sessions/"+session.getId()+"/votes")
                .body(BodyInserters.fromValue(new Vote("123123123", Answer.YES, session)))
                .exchange()
                .expectStatus()
                .isOk();

        webTestClient.post()
                .uri("sessions/"+session.getId()+"/votes")
                .body(BodyInserters.fromValue(new Vote("123123123", Answer.YES, session)))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void hasAlreadyVotedDifferentSession() {
        Agenda agenda = webTestClient.post()
                .uri("/agendas")
                .body(BodyInserters.fromValue(new Agenda("Agenda Test")))
                .exchange()
                .returnResult(Agenda.class)
                .getResponseBody()
                .next()
                .block();

        Session session1 = webTestClient.post()
                .uri("/agendas/" + agenda.getId() + "/sessions")
                .body(BodyInserters.fromValue(new Session(1L, agenda)))
                .exchange()
                .returnResult(Session.class)
                .getResponseBody()
                .next()
                .block();


        Session session2 = webTestClient.post()
                .uri("/agendas/" + agenda.getId() + "/sessions")
                .body(BodyInserters.fromValue(new Session(1L, agenda)))
                .exchange()
                .returnResult(Session.class)
                .getResponseBody()
                .next()
                .block();

        webTestClient.post()
                .uri("sessions/"+session1.getId()+"/votes")
                .body(BodyInserters.fromValue(new Vote("123123123", Answer.YES, session1)))
                .exchange()
                .expectStatus()
                .isOk();

        webTestClient.post()
                .uri("sessions/"+session2.getId()+"/votes")
                .body(BodyInserters.fromValue(new Vote("123123123", Answer.YES, session2)))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void sessionExpired() {
        Agenda agenda = webTestClient.post()
                .uri("/agendas")
                .body(BodyInserters.fromValue(new Agenda("Agenda Test")))
                .exchange()
                .returnResult(Agenda.class)
                .getResponseBody()
                .next()
                .block();

        Session session1 = new Session(agenda);
        session1.withExpiredSession(-2L);

        Session session2 = webTestClient.post()
                .uri("/agendas/" + agenda.getId() + "/sessions")
                .body(BodyInserters.fromValue(session1))
                .exchange()
                .returnResult(Session.class)
                .getResponseBody()
                .next()
                .block();

        webTestClient.post()
            .uri("sessions/" + session2.getId() + "/votes")
            .body(BodyInserters.fromValue(new Vote("123123123", Answer.YES, session2)))
            .exchange()
            .expectStatus()
            .isNotFound();

    }
}
