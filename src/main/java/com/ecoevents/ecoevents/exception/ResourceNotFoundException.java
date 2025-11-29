package com.ecoevents.ecoevents.exception;

/**
 * Custom exception thrown when a requested resource cannot be found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Create a new ResourceNotFoundException with a message.
     *
     * @param message description of what resource was not found
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
