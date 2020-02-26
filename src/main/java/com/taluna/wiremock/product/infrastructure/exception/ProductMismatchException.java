package com.taluna.wiremock.product.infrastructure.exception;

public class ProductMismatchException extends DomainException {

    public ProductMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProductMismatchException(final String message) {
        super(message);
    }

    public ProductMismatchException(final Throwable cause) {
        super(cause);
    }
}
