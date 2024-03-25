package calendar.example.calendar.controllers;

import calendar.example.calendar.entities.Event;
import calendar.example.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/createevent")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok().body(eventService.addEvent(event));
    }

    @GetMapping("/getallevents")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventService.getAllEvents());
    }

    @GetMapping("/geteventid/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> eventOptional = eventService.getEventById(id);
        if(eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } return ResponseEntity.ok().body(eventOptional.get());
    }

    @PutMapping("/updateeventid{id}")
    public ResponseEntity<Event> updateEventById(@PathVariable Long id, @RequestBody Event event) {
        Optional<Event> eventOptional = eventService.updateEventById(event, id);
        if(eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventOptional.get());
    }

    @DeleteMapping("/deleteeventid/{id}")
    public ResponseEntity<Event> deleteEventById(@PathVariable Long id) {
        Optional<Event> eventOptional = eventService.deleteEventById(id);
        if(eventOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(eventOptional.get());
    }
}
