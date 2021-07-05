package com.alchemy.utils.exceptions;

import static com.alchemy.utils.AlchemyConstants.MESSAGE_NOT_FOUND;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final String resourceName, final Object resourceId) {
        this(resourceName, "id", resourceId);
    }

    public NotFoundException(final String resourceName, final String fieldName, final Object fieldValue) {
        super(String.format(MESSAGE_NOT_FOUND, resourceName, fieldName, fieldValue));
    }
}
