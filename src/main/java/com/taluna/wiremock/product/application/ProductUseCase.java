package com.taluna.wiremock.product.application;

import com.taluna.wiremock.product.infrastructure.gateway.ProductGateway;
import com.taluna.wiremock.product.infrastructure.gateway.dto.BasicResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductUseCase {

    private final ProductGateway gateway;

    public ProductUseCase(ProductGateway gateway) {
        this.gateway = gateway;
    }

    public BasicResponse getAll(){
        return  gateway.getAll();
    }

}
