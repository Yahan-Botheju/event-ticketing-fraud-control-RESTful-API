package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Ticket;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;

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
    }

}
