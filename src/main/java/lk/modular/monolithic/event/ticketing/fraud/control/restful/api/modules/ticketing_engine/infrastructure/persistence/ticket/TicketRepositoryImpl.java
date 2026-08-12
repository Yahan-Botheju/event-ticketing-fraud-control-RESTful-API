package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.entities.TicketEntity;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.jpa.JpaTicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.persistenceMapper.TicketPersistenceMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public Optional<Ticket> findById(Long ticketId) {
        return jpaTicketRepository.findById(ticketId).map(ticketPersistenceMapper::toDomainModel);
    }

    //find ticket by code
    @Override
    @Transactional
    public Optional<Ticket> findByTicketCode(String ticketCode){
        return jpaTicketRepository.findByTicketCode(ticketCode).map(ticketPersistenceMapper::toDomainModel);
    }


    /* __PUBLIC_METHOD__ */


    //find all tickets of a user
    @Override
    public List<Ticket> findMyTickets(Long userId) {
        return jpaTicketRepository.findByOwnerId(userId).stream()
                .map(ticketPersistenceMapper::toDomainModel).toList();
    }


    //save ticket
    @Override
    @Transactional
    public Ticket save(Ticket ticket){
        TicketEntity ticketEntity = ticketPersistenceMapper.toEntity(ticket);
        TicketEntity savedTicket =  jpaTicketRepository.save(ticketEntity);

        return ticketPersistenceMapper.toDomainModel(savedTicket);
    }

}
