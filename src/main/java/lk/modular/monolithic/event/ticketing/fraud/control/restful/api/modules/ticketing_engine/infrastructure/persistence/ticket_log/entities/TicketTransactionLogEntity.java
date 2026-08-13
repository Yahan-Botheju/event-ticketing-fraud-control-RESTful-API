package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket_log.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "ticket_logs",
        schema = "table_logs"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTransactionLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String transactionLogId;
    Long ticketId;
    Long userId;
    Long eventId;
    BigDecimal ticketPrice;
    LocalDateTime timestamp;
    TicketStatus ticketStatus;
}
