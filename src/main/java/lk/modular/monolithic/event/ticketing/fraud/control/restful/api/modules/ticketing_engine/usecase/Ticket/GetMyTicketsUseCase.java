package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.GetMyTicketsCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.GetMyTicketsResult;

import java.util.List;

public interface GetMyTicketsUseCase {

    //find all tickets of a user
    List<GetMyTicketsResult> findMyTickets(GetMyTicketsCommand getMyTicketCommand);
}
