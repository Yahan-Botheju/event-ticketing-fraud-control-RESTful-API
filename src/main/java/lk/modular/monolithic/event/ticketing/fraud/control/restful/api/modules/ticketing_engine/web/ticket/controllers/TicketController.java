package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.controllers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.BuyTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.ScanTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.TransferTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.webMappers.TicketWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    //inject required dependencies
    private final BuyTicketUseCase  buyTicketUseCase;
    private final ScanTicketUseCase scanTicketUseCase;
    private final TransferTicketUseCase  transferTicketUseCase;
    private final TicketWebMapper ticketWebMapper;

    public TicketController(
            BuyTicketUseCase buyTicketUseCase,
            ScanTicketUseCase scanTicketUseCase,
            TransferTicketUseCase transferTicketUseCase,
            TicketWebMapper ticketWebMapper
    ) {
        this.buyTicketUseCase = buyTicketUseCase;
        this.scanTicketUseCase = scanTicketUseCase;
        this.transferTicketUseCase = transferTicketUseCase;
        this.ticketWebMapper = ticketWebMapper;
    }
}
