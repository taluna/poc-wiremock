package com.taluna.wiremock.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private Location location;
    private String name;
    private String createdAt;
    private Geolocation geolocation;
    private String divisionTypeName;
    private Set<String> coverImages;
}
