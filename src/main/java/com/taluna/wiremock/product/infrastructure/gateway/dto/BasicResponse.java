package com.taluna.wiremock.product.infrastructure.gateway.dto;

import com.taluna.wiremock.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse {
    private List<Product> products;
}
