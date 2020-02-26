package com.taluna.wiremock.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String address;
    private String city;
    private String neighborhood;
    private String uf;
    private String zone;
    private String number;
}
