package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.ScanTicketCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.ScanTicketResult;

public interface ScanTicketUseCase {

    //scan ticket
    ScanTicketResult execute(ScanTicketCommand scanTicketCommand);
}
