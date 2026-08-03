package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.jpa.JpaEventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.persistenceMapper.EventPersistenceMapper;

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
}
