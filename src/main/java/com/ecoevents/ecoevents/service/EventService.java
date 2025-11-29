package com.ecoevents.ecoevents.service;

import com.ecoevents.ecoevents.model.Event;
import com.ecoevents.ecoevents.repository.EventRepository;
import com.ecoevents.ecoevents.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    // Constructor injection
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Get all events from the database.
     */
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    /**
     * Get a single event by id or throw an exception.
     */
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found with id: " + id));
    }

    /**
     * Create a new event and save it to the database.
     */
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}

