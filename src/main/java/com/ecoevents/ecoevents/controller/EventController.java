package com.ecoevents.ecoevents.controller;

import com.ecoevents.ecoevents.model.Event;
import com.ecoevents.ecoevents.model.EventRegistration;
import com.ecoevents.ecoevents.model.Volunteer;
import com.ecoevents.ecoevents.repository.EventRegistrationRepository;
import com.ecoevents.ecoevents.repository.VolunteerRepository;
import com.ecoevents.ecoevents.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EventController {

    // Service for all Event-related operations
    private final EventService eventService;

    // Repositories for Volunteer and Registration data
    private final VolunteerRepository volunteerRepository;
    private final EventRegistrationRepository registrationRepository;

    // Constructor injection (recommended pattern in Spring)
    public EventController(EventService eventService,
                           VolunteerRepository volunteerRepository,
                           EventRegistrationRepository registrationRepository) {
        this.eventService = eventService;
        this.volunteerRepository = volunteerRepository;
        this.registrationRepository = registrationRepository;
    }

    /**
     * Home page: display all upcoming events.
     */
    @GetMapping("/")
    public String showHomePage(Model model) {
        // Fetch all events through the service layer
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "index";
    }

    /**
     * Show the registration form for a specific event (GET request).
     *
     * @param id    event ID from the URL
     * @param model model to pass data to the view
     */
    @GetMapping("/event/{id}/register")
    public String showRegistrationForm(@PathVariable Long id, Model model) {
        // Fetch event by ID or throw a clear exception if it does not exist
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "register";  // Renders register.html
    }

    /**
     * Process registration form submission (POST request).
     * This method links a volunteer to an event and stores the registration.
     */
    @PostMapping("/register")
    public String processRegistration(@RequestParam Long eventId,
                                      @RequestParam String name,
                                      @RequestParam String email,
                                      @RequestParam String notes) {

        // A. Find the event by ID through the service
        Event event = eventService.getEventById(eventId);

        // B. Resolve volunteer:
        //    If a volunteer with the same email already exists, reuse it;
        //    otherwise create a new volunteer record.
        Volunteer volunteer = volunteerRepository.findAll().stream()
                .filter(v -> v.getEmail().equals(email))
                .findFirst()
                .orElseGet(() -> {
                    // Create a new volunteer
                    Volunteer newVol = new Volunteer();
                    newVol.setName(name);
                    newVol.setEmail(email);
                    newVol.setMessage("Joined via web");
                    // Save to database and return the managed entity
                    return volunteerRepository.save(newVol);
                });

        // C. Create a new registration record linking event and volunteer
        EventRegistration registration = new EventRegistration();
        registration.setEvent(event);
        registration.setVolunteer(volunteer);
        registration.setNotes(notes);
        registration.setRegistrationDate(LocalDateTime.now());

        // Persist registration to the database
        registrationRepository.save(registration);

        // D. Redirect back to the home page after successful registration
        return "redirect:/";
    }
}
