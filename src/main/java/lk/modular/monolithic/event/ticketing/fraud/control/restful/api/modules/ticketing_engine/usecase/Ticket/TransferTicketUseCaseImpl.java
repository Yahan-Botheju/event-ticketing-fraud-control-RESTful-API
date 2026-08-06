package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;

public class TransferTicketUseCaseImpl implements  TransferTicketUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;

    public TransferTicketUseCaseImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
