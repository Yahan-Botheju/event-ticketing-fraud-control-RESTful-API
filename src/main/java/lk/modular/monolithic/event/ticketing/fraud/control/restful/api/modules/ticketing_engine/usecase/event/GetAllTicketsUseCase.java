package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;

import java.util.List;

public interface GetAllTicketsUseCase {

    //find all tickets of a user
    List<Ticket> findAllTicketsByUserId(Long userId);
}
