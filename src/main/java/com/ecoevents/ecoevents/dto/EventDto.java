package com.ecoevents.ecoevents.dto;

import java.time.LocalDate;

/**
 * DTO for exposing event data through the REST API.
 * It contains only the fields that API consumers should see.
 */
public class EventDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private String location;

    // Default constructor needed by frameworks like Jackson
    public EventDto() {
    }

    // Full-args constructor for convenient mapping from the entity
    public EventDto(Long id, String title, String description,
                    LocalDate eventDate, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
    }

    // Getters & setters (required for JSON serialisation)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
