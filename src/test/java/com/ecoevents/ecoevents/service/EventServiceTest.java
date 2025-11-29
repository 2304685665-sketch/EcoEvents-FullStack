package com.ecoevents.ecoevents.service;

import com.ecoevents.ecoevents.model.Event;
import com.ecoevents.ecoevents.repository.EventRepository;
import com.ecoevents.ecoevents.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;   // Fake repository used only for tests

    @InjectMocks
    private EventService eventService;         // Class under test (real object)

    @Test
    void getAllEvents_returnsAllEventsFromRepository() {
        // Arrange: prepare fake data returned by the repository
        Event e1 = new Event();
        e1.setId(1L);
        e1.setTitle("Spring Park Clean-Up");

        Event e2 = new Event();
        e2.setId(2L);
        e2.setTitle("River Clean-Up");

        List<Event> mockEvents = Arrays.asList(e1, e2);
        when(eventRepository.findAll()).thenReturn(mockEvents);

        // Act: call the service method
        List<Event> result = eventService.getAllEvents();

        // Assert: check that the service returns the expected list
        assertThat(result).hasSize(2);
        assertThat(result)
                .extracting(Event::getTitle)
                .containsExactly("Spring Park Clean-Up", "River Clean-Up");

        // Also verify that the repository method was called once
        verify(eventRepository).findAll();
    }

    @Test
    void getEventById_returnsEventWhenFound() {
        // Arrange
        Event event = new Event();
        event.setId(1L);
        event.setTitle("Spring Park Clean-Up");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        // Act
        Event result = eventService.getEventById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Spring Park Clean-Up");
        verify(eventRepository).findById(1L);
    }

    @Test
    void getEventById_throwsExceptionWhenNotFound() {
        // Arrange: repository returns empty when the id does not exist
        when(eventRepository.findById(99L)).thenReturn(Optional.empty());

        // Act + Assert: service should throw ResourceNotFoundException
        assertThatThrownBy(() -> eventService.getEventById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");

        verify(eventRepository).findById(99L);
    }

    @Test
    void createEvent_savesAndReturnsEvent() {
        // Arrange: prepare an event to save
        Event newEvent = new Event();
        newEvent.setTitle("Community Tree Planting");

        Event savedEvent = new Event();
        savedEvent.setId(10L);
        savedEvent.setTitle("Community Tree Planting");

        when(eventRepository.save(newEvent)).thenReturn(savedEvent);

        // Act
        Event result = eventService.createEvent(newEvent);

        // Assert
        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getTitle()).isEqualTo("Community Tree Planting");
        verify(eventRepository).save(newEvent);
    }

}
