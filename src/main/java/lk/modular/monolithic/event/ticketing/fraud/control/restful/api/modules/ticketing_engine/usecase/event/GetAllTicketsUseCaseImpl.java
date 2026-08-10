package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;

public class GetAllTicketsUseCaseImpl implements  GetAllTicketsUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;

    public GetAllTicketsUseCaseImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
