package calendar.example.calendar.service;

import calendar.example.calendar.entities.Event;
import calendar.example.calendar.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Optional<Event> updateEventById(Event event, Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            eventOptional.get().setName(event.getName());
            eventOptional.get().setDescription(event.getDescription());
            eventOptional.get().setDateStart(event.getDateStart());
            eventOptional.get().setDateEnd(event.getDateEnd());

            Event eventUpdated = eventRepository.save(eventOptional.get());
            return Optional.of(eventUpdated);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Event> deleteEventById(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            eventRepository.delete(eventOptional.get());
        } else {
            return Optional.empty();
        }
        return eventOptional;
    }
}
