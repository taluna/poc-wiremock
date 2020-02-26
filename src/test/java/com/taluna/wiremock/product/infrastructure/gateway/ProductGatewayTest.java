package com.taluna.wiremock.product.infrastructure.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.taluna.wiremock.product.infrastructure.gateway.dto.BasicResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWireMock(port = 8090)
public class ProductGatewayTest {

    @Autowired
    private ProductGateway gateway;

    @Test
    void getAllProducts() throws IOException {

        String response = new FixtureBuilder()
                .useFixture("all-products.json")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        BasicResponse basicResponseExpected = objectMapper.readValue(response, BasicResponse.class);

        //given
        stubFor(get(WireMock.anyUrl())
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("all-products.json")));
        //whenx
        BasicResponse basicResponse = this.gateway.getAll();

        //then
        assertThat(basicResponse).isNotNull();
        assertThat(basicResponse.getProducts()).isNotEmpty();
        assertThat(basicResponse.getProducts()).hasSize(1);
        assertThat(basicResponse.getProducts().get(0).getName()).isEqualTo(basicResponseExpected.getProducts().get(0).getName());
    }
}