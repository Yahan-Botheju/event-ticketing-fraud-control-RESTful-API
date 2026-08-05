package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import org.springframework.transaction.annotation.Transactional;

public class BuyTicketUseCaseImpl implements BuyTicketUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final RedisLockService redisLockService;
    private final String eventLockPrefix;

    public BuyTicketUseCaseImpl(
            EventRepository eventRepository,
            TicketRepository ticketRepository,
            RedisLockService redisLockService,
            String eventLockPrefix
    ) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.redisLockService = redisLockService;
        this.eventLockPrefix = eventLockPrefix;
    }

    //buy ticket
    @Override
    @Transactional
    public Ticket execute(Long ticketId, Long userId){


    }
}
