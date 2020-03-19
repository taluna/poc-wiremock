package com.taluna.wiremock.todo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.taluna.wiremock.WireMockInitializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import wiremock.org.eclipse.jetty.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {WireMockInitializer.class})
public class TodoControllerIT {

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private WebTestClient todoWebTestClient;

    @LocalServerPort
    private Integer port;

    @AfterEach
    public void afterEach() {
        this.wireMockServer.resetAll();
    }

    @Test
    public void testGetAllTodosShouldReturnDataFromClient() {
        this.wireMockServer.stubFor(
                WireMock.get("/todos")
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody("[{\"userId\": 1,\"id\": 1,\"title\": \"Learn Spring Boot 3.0\", \"completed\": false}," +
                                        "{\"userId\": 1,\"id\": 2,\"title\": \"Learn WireMock\", \"completed\": true}]"))
        );

        this.todoWebTestClient
                .get()
                .uri("http://localhost:" + port + "/api/todos")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$[0].title")
                .isEqualTo("Learn Spring Boot 3.0")
                .jsonPath("$.length()")
                .isEqualTo(2);
    }

    @Test
    public void testGetAllTodosShouldPropagateErrorMessageFromClient() {
        this.wireMockServer.stubFor(
                WireMock.get("/todos")
                        .willReturn(aResponse()
                                .withStatus(403))
        );

        this.todoWebTestClient
                .get()
                .uri("http://localhost:" + port + "/api/todos")
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR_500);
    }
}
