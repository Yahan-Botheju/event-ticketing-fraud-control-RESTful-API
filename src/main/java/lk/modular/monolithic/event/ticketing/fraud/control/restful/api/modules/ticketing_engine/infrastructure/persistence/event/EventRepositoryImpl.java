package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.entities.EventEntity;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.jpa.JpaEventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.persistenceMapper.EventPersistenceMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public Optional<Event> findById(Long eventId){
        return jpaEventRepository.findById(eventId).map(eventPersistenceMapper::toDomainModel);
    }



    /* __PUBLIC_METHODS__ */

    //get all events
    @Override
    @Transactional
    public List<Event> getAllEvents(){
        return  jpaEventRepository.findAll().stream()
                .map(eventPersistenceMapper::toDomainModel).toList();
    }


    //save event
    @Override
    @Transactional
    public Event save(Event event){
        if(jpaEventRepository.existsById(event.getEventId())) {
            throw new ConflictException("Event with id " + event.getEventId() + " already exists");
        }

        EventEntity eventEntity = eventPersistenceMapper.toEntity(event);
        EventEntity savedEntity = jpaEventRepository.save(eventEntity);

        return eventPersistenceMapper.toDomainModel(savedEntity);
    }
}
