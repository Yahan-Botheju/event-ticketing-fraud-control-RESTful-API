package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;

import java.util.Optional;

public interface EventRepository {

    //event find by id
    Optional<Event> findById(Long eventId);

    //save event
    Event save(Event event);

}
