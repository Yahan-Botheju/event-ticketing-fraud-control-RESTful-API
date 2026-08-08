package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.scan_ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.InvalidTicketException;

import java.util.UUID;

public class ScanTicketUseCaseImpl implements  ScanTicketUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;
    private final RedisLockService redisLockService;
    private final String ticketScanLockPrefix;
    private final Long ticketScanExpirationSeconds;

    public ScanTicketUseCaseImpl(
            TicketRepository ticketRepository,
            RedisLockService redisLockService,
            String ticketScanLockPrefix,
            Long ticketScanExpirationSeconds
    ) {
        this.ticketRepository = ticketRepository;
        this.redisLockService = redisLockService;
        this.ticketScanLockPrefix = ticketScanLockPrefix;
        this.ticketScanExpirationSeconds = ticketScanExpirationSeconds;
    }

    //scan ticket
    @Override
    public Ticket execute(String ticketCode){
        //generate scan ticket key
        String localKey = ticketScanLockPrefix + ticketCode;
        //lock value
        String lockValue = UUID.randomUUID().toString();

        //create lock preventing scan twice in same time
        boolean isLocked = redisLockService.acquireLock(localKey, lockValue, ticketScanExpirationSeconds);

        if(!isLocked){
            throw new InvalidTicketException("Ticket scan in processed, try again later.");
        }

        try{
            //check ticket availability
            Ticket existingTicket = ticketRepository.findByTicketCode(ticketCode)
                    .orElseThrow(() -> new InvalidTicketException("Ticket code not found."));

            //set ticket as used
            existingTicket.markAsUsed();

            return ticketRepository.save(existingTicket);
        }finally {
            redisLockService.releaseLock(localKey, lockValue);
        }

    }

}
