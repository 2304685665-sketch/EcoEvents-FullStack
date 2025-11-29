package com.ecoevents.ecoevents.exception;

import java.time.Instant;

/**
 * Simple DTO for API error responses.
 */
public class ApiError {

    private int status;
    private String message;
    private Instant timestamp;

    public ApiError() {
    }

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
