package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;

import java.util.Optional;

public interface TicketRepository {

    //find ticket by id
    Optional<Ticket> findById(Long ticketId);

    //save ticket
    Ticket save(Ticket ticket);
}
