package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.TransferTicketCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.TransferTicketResult;

public interface TransferTicketUseCase {
    //transfer a ticket
    TransferTicketResult transferTicket(TransferTicketCommand transferTicketCommand);
}
