package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.entities.EventEntity;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.jpa.JpaEventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.persistenceMapper.EventPersistenceMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.ConflictException;

import java.util.Optional;

public class EventRepositoryImpl implements EventRepository {

    //inject required dependencies
    private final JpaEventRepository jpaEventRepository;
    private final EventPersistenceMapper eventPersistenceMapper;

    public EventRepositoryImpl(
            JpaEventRepository jpaEventRepository,
            EventPersistenceMapper eventPersistenceMapper
    ) {
        this.jpaEventRepository = jpaEventRepository;
        this.eventPersistenceMapper = eventPersistenceMapper;
    }

    /* __HELPER_METHODS__ */

    //event find by id
    public Optional<Event> findById(Long eventId){
        return jpaEventRepository.findById(eventId).map(eventPersistenceMapper::toDomainModel);
    }



    /* __PUBLIC_METHODS__ */

    //save event
    @Override
    public Event save(Event event){
        if(jpaEventRepository.existsById(event.getEventId())) {
            throw new ConflictException("Event with id " + event.getEventId() + " already exists");
        }

        EventEntity eventEntity = eventPersistenceMapper.toEntity(event);
        EventEntity savedEntity = jpaEventRepository.save(eventEntity);

        return eventPersistenceMapper.toDomainModel(savedEntity);
    }
}
