package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure._config._persistenceBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.EventRepositoryImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.jpa.JpaEventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.persistenceMapper.EventPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceBeanConfig {
    @Bean
    public EventRepository eventRepository(
            JpaEventRepository jpaEventRepository,
            EventPersistenceMapper eventPersistenceMapper
    ) {
        return new EventRepositoryImpl(jpaEventRepository, eventPersistenceMapper);
    }
}
