package com.taluna.wiremock.product.interfaces.rest;

import com.taluna.wiremock.product.application.ProductUseCase;
import com.taluna.wiremock.product.infrastructure.gateway.dto.BasicResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductUseCase useCase;

    public ProductController(ProductUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public BasicResponse getAllProducts() {
        return useCase.getAll();
    }

}
