package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure._config._persistenceBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventPublisher;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketTransactionLogRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.eventPublisher.EventPublisherImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.eventPublisher.listeners.TicketLogEventListener;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.EventRepositoryImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.jpa.JpaEventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.persistenceMapper.EventPersistenceMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.TicketRepositoryImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.jpa.JpaTicketRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.persistenceMapper.TicketPersistenceMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceBeanConfig {

    //event persistence implementation bean config
    @Bean
    public EventRepository eventRepository(
            JpaEventRepository jpaEventRepository,
            EventPersistenceMapper eventPersistenceMapper
    ) {
        return new EventRepositoryImpl(jpaEventRepository, eventPersistenceMapper);
    }

    //ticket persistence implementation bean config
    @Bean
    public TicketRepository ticketRepository(
            JpaTicketRepository jpaTicketRepository,
            TicketPersistenceMapper ticketPersistenceMapper
    ){
        return new TicketRepositoryImpl(jpaTicketRepository, ticketPersistenceMapper);
    }

    //event publisher persistence impl
    @Bean
    public EventPublisher eventPublisher(
        ApplicationEventPublisher applicationEventPublisher
    ){
        return new EventPublisherImpl(applicationEventPublisher);
    }

    //ticket log event listener
    @Bean
    public TicketLogEventListener  ticketLogEventListener(
            TicketTransactionLogRepository ticketTransactionLogRepository
    ){
        return new TicketLogEventListener(ticketTransactionLogRepository);
    }
}
