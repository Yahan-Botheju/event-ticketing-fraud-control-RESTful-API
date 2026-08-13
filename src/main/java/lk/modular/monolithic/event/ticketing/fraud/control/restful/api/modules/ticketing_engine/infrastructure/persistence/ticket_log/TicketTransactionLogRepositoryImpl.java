package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.records.TicketTransactionLog;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketTransactionLogRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log.entities.TicketTransactionLogEntity;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log.jpa.JpaTicketTransactionLogRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log.persistenceMapper.TicketTransactionLogPersistenceMapper;

public class TicketTransactionLogRepositoryImpl implements TicketTransactionLogRepository {

    //inject required dependencies
    private final JpaTicketTransactionLogRepository jpaTicketTransactionLogRepository;
    private final TicketTransactionLogPersistenceMapper ticketTransactionLogPersistenceMapper;

    public TicketTransactionLogRepositoryImpl(
            JpaTicketTransactionLogRepository jpaTicketTransactionLogRepository,
            TicketTransactionLogPersistenceMapper ticketTransactionLogPersistenceMapper
    ) {
        this.jpaTicketTransactionLogRepository = jpaTicketTransactionLogRepository;
        this.ticketTransactionLogPersistenceMapper = ticketTransactionLogPersistenceMapper;
    }

    @Override
    public void save(TicketTransactionLog ticketTransactionLog) {
        TicketTransactionLogEntity toEntity =
                ticketTransactionLogPersistenceMapper.toEntity(ticketTransactionLog);

        jpaTicketTransactionLogRepository.save(toEntity);
    }
}
