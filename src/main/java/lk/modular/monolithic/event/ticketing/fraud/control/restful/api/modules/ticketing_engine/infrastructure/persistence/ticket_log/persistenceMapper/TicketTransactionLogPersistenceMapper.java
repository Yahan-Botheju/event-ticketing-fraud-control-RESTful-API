package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log.persistenceMapper;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.ticket_log_records.TicketTransactionLog;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log.entities.TicketTransactionLogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketTransactionLogPersistenceMapper {

    //domain model to entity
    TicketTransactionLogEntity toEntity(TicketTransactionLog ticketTransactionLog);
}
