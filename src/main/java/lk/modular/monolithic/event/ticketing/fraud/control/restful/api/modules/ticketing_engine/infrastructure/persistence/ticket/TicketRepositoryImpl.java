package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.jpa.JpaTicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.persistenceMapper.TicketPersistenceMapper;

public class TicketRepositoryImpl implements TicketRepository {

    //inject required dependencies
    private final JpaTicketRepository jpaTicketRepository;
    private final TicketPersistenceMapper ticketPersistenceMapper;

    public TicketRepositoryImpl(
            JpaTicketRepository jpaTicketRepository,
            TicketPersistenceMapper ticketPersistenceMapper
    ) {
        this.jpaTicketRepository = jpaTicketRepository;
        this.ticketPersistenceMapper = ticketPersistenceMapper;
    }
}
