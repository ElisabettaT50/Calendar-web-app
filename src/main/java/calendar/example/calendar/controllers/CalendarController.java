package calendar.example.calendar.controllers;

import calendar.example.calendar.entities.Calendar;
import calendar.example.calendar.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Calendario" ,description = "controller delle APIs per il calendario")
@RestController
@RequestMapping("/calendario")
public class CalendarController {
    @Autowired
    private CalendarService service;
    @Operation(
            summary = "create and save new Calendar",
            description = "needs a new Calndar in JSON format." +
                    "Save the required obj on database."+
                    "The response is the Calendar obj just created with id, name, description, color, list of events." ,
            tags = { "Calendar", "post" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendar.class), mediaType = "application/json") })
    @PostMapping("/create")
    public ResponseEntity<Calendar> postCalendar(@RequestBody Calendar calendar){
        return ResponseEntity.ok().body(service.addCalendar(calendar));
    }
    @Operation(
            summary = "Provides the list of al Calendars present",
            description = "needs the Calendar list." +
                    "The response is the Calendar obj just created with id, name, description, color, list of events." ,
            tags = { "Calendario", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendar.class), mediaType = "application/json") })
    @GetMapping("/getall")
    public ResponseEntity<List<Calendar>> getCalendari(){
        return ResponseEntity.ok().body(service.getCalendars());
    }
    @Operation(
            summary = "Provides a Calendar from id",
            description = "needs a Calendar with id given." +
                    "The response is the Calendar obj just created with id, name, description, color, list of events." ,
            tags = { "Calendar", "get" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendar.class), mediaType = "application/json") })
    @GetMapping("/get/{id}")
    public ResponseEntity<Calendar> getCalendar(@PathVariable Long id){
        Optional<Calendar> calendarOptional = service.getCalendarById(id);
        if(calendarOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarOptional.get());
    }
    @Operation(
            summary = "Provides a Calendar from id and needs data to be updated in JSON",
            description = "needs data to be updated in JSON"+
                    "Provides a Calendar from id and updates it with input data then saves it." +
                    "The response is the Calendar obj just created with id, name, description, color, list of events." ,
            tags = { "Calendar", "put" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendar.class), mediaType = "application/json") })
    @PutMapping("/update/{id}")
    public ResponseEntity<Calendar> putCalendar(@PathVariable Long id,@RequestBody Calendar calendar){
        Optional<Calendar> calendarOptional = service.updateCalendar(calendar,id);
        if(calendarOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarOptional.get());
    }
    @Operation(
            summary = "Provides a Calendar from id and deletes it",
            description = "needs a Calendar with id given." +
                    "Once Calendar is provided, it deletes it." +
                    "The response is the Calendar obj just deleted with id, name, description, color, list of events.." ,
            tags = { "Calendar", "delete" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendar.class), mediaType = "application/json") })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Calendar> deleteCalendar(@PathVariable Long id){
        Optional<Calendar> calendarOptional = service.deleteCalendarById(id);
        if(calendarOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarOptional.get());
    }
    @Operation(
            summary = "Provides a Calendar from id and adds an Event from its id.",
            description = "needs a Calendar and and an Event with id given." +
                    "once Calendar is provided, Event is provided too then saved in the list of event." +
                    "The response is the Calendar obj just deleted with id, name, description, color, list of events.." ,
            tags = { "Calendar", "put" })
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Calendar.class), mediaType = "application/json") })
    @PutMapping("/addevent/{id}/")
    public ResponseEntity<Calendar> addEvent(@PathVariable Long id, @RequestParam Long event){
        Optional<Calendar> calendarOptional = service.addEventToCalendar(id,event);
        if(calendarOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(calendarOptional.get());
    }

}