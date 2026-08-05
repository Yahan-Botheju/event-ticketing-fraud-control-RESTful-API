package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;

public class BuyTicketUseCaseImpl implements BuyTicketUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final RedisLockService redisLockService;

    public BuyTicketUseCaseImpl(
            TicketRepository ticketRepository,
            EventRepository eventRepository,
            RedisLockService redisLockService
    ) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.redisLockService = redisLockService;
    }
}
