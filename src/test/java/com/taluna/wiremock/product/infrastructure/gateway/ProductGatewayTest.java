package com.taluna.wiremock.product.infrastructure.gateway;

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

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {WireMockInitializer.class})
public class ProductGatewayTest {

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private WebTestClient productsWebTestClient;

    @LocalServerPort
    private Integer port;

    @AfterEach
    public void afterEach() {
        this.wireMockServer.resetAll();
    }

    @Test
    void getAllProducts() {
        this.wireMockServer.stubFor(
                WireMock.get("/products")
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("all-products.json")));
        this.productsWebTestClient
                .get()
                .uri("http://localhost:" + port + "/api/products")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.products").isArray()
                .jsonPath("$.products.length()").isEqualTo(1)
                //.jsonPath("$.products[0].title").isEqualTo("Learn Spring Boot 3.0")
                ;


    }
}