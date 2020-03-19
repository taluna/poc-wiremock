package com.taluna.wiremock.product.infrastructure.gateway;

import com.taluna.wiremock.product.infrastructure.gateway.dto.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductGateway {

    private WebClient productWebClient;

    public ProductGateway(WebClient productWebClient) {
        this.productWebClient = productWebClient;
    }

    public BasicResponse getAll() {

        return productWebClient
                .get()
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new IllegalArgumentException())
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR))
                )
                .bodyToMono(BasicResponse.class)
                .block();
    }
}
