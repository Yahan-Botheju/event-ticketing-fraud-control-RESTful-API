package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;

public interface BuyTicketUseCase {

    //buy ticket
    Ticket execute(Long eventId, Long userId);
}
