package rocketseat.com.pass_in.services;

import org.springframework.stereotype.Service;
import rocketseat.com.pass_in.domain.attendee.Attendee;
import rocketseat.com.pass_in.domain.event.Event;
import rocketseat.com.pass_in.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.pass_in.dto.event.EventIdDTO;
import rocketseat.com.pass_in.dto.event.EventRequestDTO;
import rocketseat.com.pass_in.dto.event.EventResponseDTO;
import rocketseat.com.pass_in.repositories.EventRepository;

import java.text.Normalizer;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventService(EventRepository eventRepository, AttendeeService attendeeService) {
        this.eventRepository = eventRepository;
        this.attendeeService = attendeeService;
    }

    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID:" + eventId));
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());

    }

    public EventIdDTO createdEvent(EventRequestDTO eventDTO) {
        Event newEvent;
        newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetail(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}
