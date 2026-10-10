package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.GetMyTicketCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.GetMyTicketResult;

import java.util.List;

public interface GetMyTicketsUseCase {

    //find all tickets of a user
    List<GetMyTicketResult> findMyTickets(GetMyTicketCommand getMyTicketCommand);
}
