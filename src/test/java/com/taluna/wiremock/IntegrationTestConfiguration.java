package com.taluna.wiremock;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.reactive.server.WebTestClient;

@Configuration
public class IntegrationTestConfiguration {

    private @Value("${base_url}") String base_url;
    private @Value("${todo_url}") String todo_url;

    @Bean
    @Qualifier("productsWebTestClient")
    WebTestClient productsWebTestClient(){
        return  WebTestClient
                .bindToServer()
                .baseUrl(base_url)
                .build();
    }

    @Bean
    @Qualifier("todoWebTestClient")
    WebTestClient todoWebTestClient(){
        return  WebTestClient
                .bindToServer()
                .baseUrl(todo_url)
                .build();
    }
}
