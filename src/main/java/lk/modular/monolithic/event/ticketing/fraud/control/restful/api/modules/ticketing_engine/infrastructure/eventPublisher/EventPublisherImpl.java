package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.eventPublisher;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;

public class EventPublisherImpl implements EventPublisher {

    //inject required dependencies
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherImpl(
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
