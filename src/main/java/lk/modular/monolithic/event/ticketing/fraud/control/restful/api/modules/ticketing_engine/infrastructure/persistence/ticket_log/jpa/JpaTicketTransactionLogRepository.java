package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log.jpa;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log.entities.TicketTransactionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTicketTransactionLogRepository extends JpaRepository<TicketTransactionLogEntity, String> {
}
