package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.entities.TicketEntity;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.jpa.JpaTicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.persistenceMapper.TicketPersistenceMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;

import java.util.Optional;

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

    /* __HELPER_METHOD__ */

    //ticket find by id
    @Override
    public Optional<Ticket> findById(Long ticketId) {
        return jpaTicketRepository.findById(ticketId).map(ticketPersistenceMapper::toDomainModel);
    }

    //find ticket by code
    @Override
    public Optional<Ticket> findByTicketCode(String ticketCode){
        return jpaTicketRepository.findByTicketCode(ticketCode).map(ticketPersistenceMapper::toDomainModel);
    }

    /* __PUBLIC_METHOD__ */

    //save ticket
    @Override
    public Ticket save(Ticket ticket){
        if (jpaTicketRepository.existsById(ticket.getTicketId())){
            throw new ConflictException("Ticket already exists");
        }
        TicketEntity ticketEntity = ticketPersistenceMapper.toEntity(ticket);
        TicketEntity savedTicket =  jpaTicketRepository.save(ticketEntity);

        return ticketPersistenceMapper.toDomainModel(savedTicket);
    }
}
