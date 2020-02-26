package com.taluna.wiremock.product.infrastructure.gateway;

import com.taluna.wiremock.product.infrastructure.gateway.dto.BasicResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProductGateway {

    @Value("${base_url}")
    private String baseURL;

    public BasicResponse getAll() {

        var webClient = WebClient.builder()
                .baseUrl(baseURL)
                .build();

        return webClient
                .get()
                .retrieve()
                .bodyToMono(BasicResponse.class)
                .block();
    }

    public String getBaseURL(){
        return this.baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }
}