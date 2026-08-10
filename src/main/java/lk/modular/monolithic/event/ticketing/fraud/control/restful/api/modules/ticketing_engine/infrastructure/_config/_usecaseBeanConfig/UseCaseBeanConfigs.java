package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure._config._usecaseBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.BuyTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.BuyTicketUseCaseImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.ScanTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.ScanTicketUseCaseImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.TransferTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.TransferTicketUseCaseImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.shared_domain.SharedRepositories.UserValidationClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfigs {


    /* __TIMES_AND_PREFIX__*/


    /* --------------------- */


    //redis lock event prefix
    @Value("${application.security.redis.lock.event-prefix}")
    private String eventLockPrefix;

    @Bean
    public String eventLockPrefix(){
        return this.eventLockPrefix;
    }


    /* --------------------- */


    //redis expiration time
    @Value("${application.security.redis.lock.expiration-second}")
    private Long redisLockExpirationSeconds;

    @Bean
    public Long redisLockExpirationSeconds(){
        return this.redisLockExpirationSeconds;
    }


    /* --------------------- */


    //ticket code prefix
    @Value("${application.security.ticketing.code-prefix}")
    private String ticketCodePrefix;

    @Bean
    public String ticketCodePrefix(){
        return this.ticketCodePrefix;
    }


    /* --------------------- */


    //redis lock ticket scan prefix
    @Value("${application.security.redis.lock.ticket-scan-prefix}")
    private String ticketScanLockPrefix;

    @Bean
    public String ticketScanLockPrefix(){
        return this.ticketScanLockPrefix;
    }


    /* --------------------- */


    //redis lock ticket scan expiration time
    @Value("${application.security.redis.lock.ticket-scan-expiration-second}")
    private Long ticketScanExpirationSeconds;

    @Bean
    public Long ticketScanExpirationSeconds(){
        return this.ticketScanExpirationSeconds;
    }



    /* __USE_CASES__*/


    //create event usecase impl
    @Bean
    public CreateEventUseCase createEventUseCase(
            EventRepository eventRepository
    ) {
        return new CreateEventUseCaseImpl(eventRepository);
    }

    //buy ticket usecase impl
    @Bean
    public BuyTicketUseCase buyTicketUseCase(
            EventRepository eventRepository,
            TicketRepository ticketRepository,
            RedisLockService redisLockService
    ){

        return new BuyTicketUseCaseImpl(
                eventRepository,
                ticketRepository,
                redisLockService,
                eventLockPrefix,
                redisLockExpirationSeconds,
                ticketCodePrefix
        );
    }

    //scan ticket usecase impl
    @Bean
    public ScanTicketUseCase scanTicketUseCase(
            TicketRepository ticketRepository,
            RedisLockService redisLockService
    ){
        return new ScanTicketUseCaseImpl(
                ticketRepository,
                redisLockService,
                ticketScanLockPrefix,
                ticketScanExpirationSeconds
        );
    }

    //transfer ticket usecase impl
    @Bean
    public TransferTicketUseCase transferTicketUseCase(
            TicketRepository ticketRepository
    ){
        return new TransferTicketUseCaseImpl(ticketRepository);
    }


    //get all events use case impl
    @Bean
    public GetAllEventsUseCase getAllEventsUseCase(
            EventRepository eventRepository
    ){
        return new GetAllEventsUseCaseImpl(eventRepository);
    }

    //get event by id use case Impl
    @Bean
    public EventByIdUseCase  eventByIdUseCase(
            EventRepository eventRepository
    ){
        return new EventByIdUseCaseImpl(eventRepository);
    }

    //get all tickets of a user
    @Bean
    public GetMyTicketsUseCase getAllTicketsUseCase(
            TicketRepository ticketRepository,
            UserValidationClientRepository  userValidationClientRepository
    ){
        return new GetMyTicketsUseCaseImpl(ticketRepository, userValidationClientRepository);
    }
}
