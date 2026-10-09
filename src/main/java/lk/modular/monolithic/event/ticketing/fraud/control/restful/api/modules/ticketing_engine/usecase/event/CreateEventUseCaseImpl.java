package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.CreateEventCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.CreateEventResult;

import java.time.LocalDateTime;

public class CreateEventUseCaseImpl implements CreateEventUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;

    public CreateEventUseCaseImpl(
            EventRepository eventRepository
    ) {
        this.eventRepository = eventRepository;
    }

    //create event
    @Override
    public CreateEventResult execute(CreateEventCommand createEventCommand) {

        LocalDateTime currentDateTime = LocalDateTime.now();

        //create event model through domain
        Event newEvent = Event.createNewEvent(
                createEventCommand.eventTitle(),
                createEventCommand.eventDescription(),
                createEventCommand.eventLocation(),
                createEventCommand.eventDate(),
                createEventCommand.eventTotalTickets(),
                createEventCommand.eventTicketPrice(),
                createEventCommand.organizerId(),
                currentDateTime
        );

        Event savedEvent = eventRepository.save(newEvent);

        return new CreateEventResult(
                savedEvent.getEventId().toString(),
                savedEvent.getEventTitle(),
                savedEvent.getEventDescription(),
                savedEvent.getEventLocation(),
                savedEvent.getEventDate().toString(),
                savedEvent.getEventTotalTickets().toString(),
                savedEvent.getEventAvailableTickets().toString(),
                savedEvent.getEventTicketPrice().toString(),
                savedEvent.getOrganizerId().toString(),
                savedEvent.getCreatedAt().toString()
        );
    }
}
