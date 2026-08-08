package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.TicketStatus;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

public class BuyTicketUseCaseImpl implements BuyTicketUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final RedisLockService redisLockService;
    private final String eventLockPrefix;
    private final long redisLockExpirationSeconds;
    private final String ticketLockPrefix;

    public BuyTicketUseCaseImpl(
            EventRepository eventRepository,
            TicketRepository ticketRepository,
            RedisLockService redisLockService,
            String eventLockPrefix,
            long redisLockExpirationSeconds,
            String ticketLockPrefix

    ) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.redisLockService = redisLockService;
        this.eventLockPrefix = eventLockPrefix;
        this.redisLockExpirationSeconds = redisLockExpirationSeconds;
        this.ticketLockPrefix = ticketLockPrefix;
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
            //check event availability
            Event existingEvent = eventRepository.findById(eventId)
                   .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

            //check ticket availability and reserving
            existingEvent.reserveTicket(1);

            //save tickets in db
            eventRepository.save(existingEvent);

            //generate secure ticket code
            String secureTicketCode = ticketLockPrefix + UUID.randomUUID().toString();

            //create new ticket
            Ticket ticket = new Ticket(
                    null,
                    secureTicketCode,
                    eventId,
                    userId,
                    existingEvent.getEventTicketPrice(),
                    TicketStatus.PURCHASED,
                    LocalDateTime.now(),
                    null
            );

            return  ticketRepository.save(ticket);

        }finally {
            redisLockService.releaseLock(lockKey, lockValue);
        }
    }
}
