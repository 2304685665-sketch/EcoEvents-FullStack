package com.ecoevents.ecoevents.controller;

import com.ecoevents.ecoevents.dto.EventDto;
import com.ecoevents.ecoevents.model.Event;
import com.ecoevents.ecoevents.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST API controller that exposes event data as JSON
 * for front-end or mobile clients.
 */
@RestController   // This controller returns JSON instead of HTML views
@RequestMapping("/api/v1/events") // Versioned and resource-based API path
public class EventApiController {

    private final EventService eventService;

    // Constructor injection of the service layer
    public EventApiController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * GET /api/v1/events
     * Returns all events as a JSON array of EventDto objects.
     */
    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        // Fetch all events from the service layer
        List<Event> events = eventService.getAllEvents();

        // Map JPA entities to DTOs that are safe to expose to API clients
        List<EventDto> dtos = events.stream()
                .map(event -> new EventDto(
                        event.getId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getEvent_date(),   // Use your actual getter name here
                        event.getLocation()
                ))
                .collect(Collectors.toList());

        // Return HTTP 200 OK with the list of DTOs in the response body
        return ResponseEntity.ok(dtos);
    }

    /**
     * GET /api/v1/events/{id}
     * Returns a single event by its ID as JSON.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        // Delegate to the service layer, which throws ResourceNotFoundException
        Event event = eventService.getEventById(id);

        // Map entity to DTO
        EventDto dto = new EventDto(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getEvent_date(),   // Same getter as above
                event.getLocation()
        );

        return ResponseEntity.ok(dto);
    }
}
