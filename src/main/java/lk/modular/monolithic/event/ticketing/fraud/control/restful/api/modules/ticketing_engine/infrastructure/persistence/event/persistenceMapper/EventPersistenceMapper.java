package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.persistenceMapper;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.entities.EventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventPersistenceMapper {

    //domain model to entity
    EventEntity toEntity(Event event);

    //entity to domain model
    Event toDomainModel(EventEntity eventEntity);
}
