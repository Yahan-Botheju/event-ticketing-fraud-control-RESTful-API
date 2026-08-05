package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure._config._usecaseBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.BuyTicketUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.BuyTicketUseCaseImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.CreateEventUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.CreateEventUseCaseImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class UseCaseBeanConfigs {

    //redis lock event prefix
    @Value("${application.security.redis.lock.event-prefix}")
    private String eventLockPrefix;

    @Bean
    public String eventLockPrefix(){
        return this.eventLockPrefix;
    }

    //redis expiration time
    @Value("${application.security.redis.lock.expiration-second}")
    private Long redisLockExpirationSeconds;

    @Bean
    public Long redisLockExpirationSeconds(){
        return this.redisLockExpirationSeconds;
    }

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
//            @Value("${application.security.ticketing.code-prefix}") String ticketCodePrefix
    ){

        return new BuyTicketUseCaseImpl(
                eventRepository,
                ticketRepository,
                redisLockService,
                this.eventLockPrefix,
                redisLockExpirationSeconds

        );
    }
}
