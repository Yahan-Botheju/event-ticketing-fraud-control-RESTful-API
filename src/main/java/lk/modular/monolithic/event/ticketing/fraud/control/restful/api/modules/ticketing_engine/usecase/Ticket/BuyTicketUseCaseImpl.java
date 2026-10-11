package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.TicketStatus;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.ticket_log_records.TicketPurchasedEvent;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventPublisher;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.BuyTicketCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records.BuyTicketResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class BuyTicketUseCaseImpl implements BuyTicketUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final RedisLockService redisLockService;
    private final EventPublisher eventPublisher;
    private final String eventLockPrefix;
    private final long redisLockExpirationSeconds;
    private final String ticketCodePrefix;

    public BuyTicketUseCaseImpl(
            EventRepository eventRepository,
            TicketRepository ticketRepository,
            RedisLockService redisLockService,
            EventPublisher eventPublisher,
            String eventLockPrefix,
            long redisLockExpirationSeconds,
            String ticketCodePrefix

    ) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.redisLockService = redisLockService;
        this.eventPublisher = eventPublisher;
        this.eventLockPrefix = eventLockPrefix;
        this.redisLockExpirationSeconds = redisLockExpirationSeconds;
        this.ticketCodePrefix = ticketCodePrefix;
    }

    //buy ticket
    @Override
    public BuyTicketResult execute(BuyTicketCommand buyTicketCommand){
        //create lock and identifier value
        String lockKey = eventLockPrefix + buyTicketCommand.eventId();
        String lockValue = UUID.randomUUID().toString();

        //lock ticket buying process for per user
        boolean isLocked = redisLockService.acquireLock(lockKey, lockValue, redisLockExpirationSeconds);

        //check locked status
        if(!isLocked){
            throw new ConflictException("System is buys processing requests, try again later");
        }

        try {
            //check event availability
            Event existingEvent = eventRepository.findById(buyTicketCommand.eventId())
                   .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

            //check ticket availability and reserving
            existingEvent.reserveTicket(1);

            //save tickets in db
            eventRepository.save(existingEvent);

            //generate secure ticket code
            String secureTicketCode = ticketCodePrefix + UUID.randomUUID();

            LocalDateTime currentTime = LocalDateTime.now();

            //create new ticket
            Ticket newTicket = Ticket.createNewTicket(
                    null,
                    secureTicketCode,
                    existingEvent.getEventId(),
                    buyTicketCommand.userId(),
                    existingEvent.getEventTicketPrice(),
                    TicketStatus.PURCHASED,
                    currentTime,
                    null
            );


            //save ticket
            Ticket savedTicket = ticketRepository.save(newTicket);

            //publish the event
             eventPublisher.publish(new TicketPurchasedEvent(
                    savedTicket.getTicketId(),
                    savedTicket.getOwnerId(),
                    savedTicket.getEventId(),
                    savedTicket.getTicketPrice()
            ));


            return new BuyTicketResult(
                    savedTicket.getTicketId().toString(),
                    savedTicket.getTicketCode(),
                    savedTicket.getEventId().toString(),
                    savedTicket.getOwnerId().toString(),
                    savedTicket.getTicketPrice().toString(),
                    savedTicket.getTicketStatus().toString(),
                    savedTicket.getPurchasedAt().toString(),
                    savedTicket.getScannedAt().toString()
            );

        }finally {
            redisLockService.releaseLock(lockKey, lockValue);
        }
    }
}
