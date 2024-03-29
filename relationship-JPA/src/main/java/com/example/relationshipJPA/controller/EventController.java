package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Entity.Event;
import com.example.relationshipJPA.Service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/auth")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody Event request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        eventService.RaiseEvent(request, username);
        return ResponseEntity.ok("successfully created event");
    }

    @GetMapping("/getevents")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Event> updateevent(@RequestBody Event request, @PathVariable("id") Long funcid) {
        Event updatedevent = eventService.updateEvent(request, funcid);
        return ResponseEntity.ok(updatedevent);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteevent(@PathVariable("id") Long funcid) {
        eventService.deleteEvent(funcid);
        return ResponseEntity.ok("event deleted");
    }

    //doubt
    @PostMapping("/checkBookings")
    public ResponseEntity<String> checkDate(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
        if (eventService.checkDateAvailable(dateFrom, dateTo)) {
            return new ResponseEntity<>("Bookings available", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking not available for this date", HttpStatus.BAD_REQUEST);
        }
    }
}
