package rocketseat.com.pass_in.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.pass_in.dto.attendee.AttendeesListResponseDTO;
import rocketseat.com.pass_in.dto.event.EventIdDTO;
import rocketseat.com.pass_in.dto.event.EventRequestDTO;
import rocketseat.com.pass_in.dto.event.EventResponseDTO;
import rocketseat.com.pass_in.services.AttendeeService;
import rocketseat.com.pass_in.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    public EventController(EventService service, AttendeeService attendeeService) {
        this.eventService = service;
        this.attendeeService = attendeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO event = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = this.eventService.createdEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){
        AttendeesListResponseDTO attendeesListResponse = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeesListResponse);
    }
}
