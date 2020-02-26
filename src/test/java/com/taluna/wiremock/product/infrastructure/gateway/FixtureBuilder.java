package com.taluna.wiremock.product.infrastructure.gateway;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;

public class FixtureBuilder {

    private String jsonData;

    public FixtureBuilder() {
        this.jsonData = "";
    }

    public FixtureBuilder useFixture(String location)  {
        FileInputStream jsonFixtureFile = null;
        try {
            jsonFixtureFile = new FileInputStream(new File("src/test/resources/__files/" + location));

            this.jsonData = IOUtils.toString(jsonFixtureFile, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public String build() {
        return this.jsonData;
    }
}
