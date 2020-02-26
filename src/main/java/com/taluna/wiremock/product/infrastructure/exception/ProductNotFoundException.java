package com.taluna.wiremock.product.infrastructure.exception;

public class ProductNotFoundException extends DomainException {

    public ProductNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(final String message) {
        super(message);
    }

    public ProductNotFoundException(final Throwable cause) {
        super(cause);
    }
}
