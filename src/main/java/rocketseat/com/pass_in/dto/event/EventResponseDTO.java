package rocketseat.com.pass_in.dto.event;

import rocketseat.com.pass_in.domain.event.Event;

public class EventResponseDTO {

    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees){
        this.event = new EventDetailDTO(event.getId(), event.getTitle(), event.getDetail(), event.getSlug(), event.getMaximumAttendees(), numberOfAttendees);
    }

    public EventDetailDTO getEvent() {
        return event;
    }
}
