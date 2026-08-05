package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.ConflictException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class BuyTicketUseCaseImpl implements BuyTicketUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final RedisLockService redisLockService;
    private final String eventLockPrefix;
    private final long redisLockExpirationSeconds;

    public BuyTicketUseCaseImpl(
            EventRepository eventRepository,
            TicketRepository ticketRepository,
            RedisLockService redisLockService,
            String eventLockPrefix,
            long redisLockExpirationSeconds
    ) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.redisLockService = redisLockService;
        this.eventLockPrefix = eventLockPrefix;
        this.redisLockExpirationSeconds = redisLockExpirationSeconds;
    }

    //buy ticket
    @Override
    @Transactional
    public Ticket execute(Long eventId, Long userId){
        //create lock and identifier value
        String lockKey = eventLockPrefix + eventId;
        String lockValue = UUID.randomUUID().toString();

        //lock ticket buying process for per user
        boolean isLocked = redisLockService.acquireLock(lockKey, lockValue, redisLockExpirationSeconds);

        //check locked status
        if(!isLocked){
            throw new ConflictException("System is buys processing requests, try again later");
        }

        try {

            Event event = eventRepository.findById()

        }finally {
            redisLockService.releaseLock(lockKey, lockValue);
        }
    }
}
