package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;

public class ScanTicketUseCaseImpl implements  ScanTicketUseCase {

    //inject required dependencies
    private final TicketRepository ticketRepository;
    private final RedisLockService redisLockService;

    public ScanTicketUseCaseImpl(
            TicketRepository ticketRepository,
            RedisLockService redisLockService
    ) {
        this.ticketRepository = ticketRepository;
        this.redisLockService = redisLockService;
    }
}
