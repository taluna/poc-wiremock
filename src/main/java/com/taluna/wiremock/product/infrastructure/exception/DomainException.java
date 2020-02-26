package com.taluna.wiremock.product.infrastructure.exception;

public class DomainException extends RuntimeException {

    public DomainException() {
        super();
    }

    public DomainException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DomainException(final String message) {
        super(message);
    }

    public DomainException(final Throwable cause) {
        super(cause);
    }
}
