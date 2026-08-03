package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.ticket.entities;

import jakarta.persistence.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets", schema = "ticketing_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "ticket_code", nullable = false, unique = true)
    private String ticketCode;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "ticket_price", nullable = false, precision = 10,scale = 2)
    private BigDecimal ticketPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status", nullable = false)
    private TicketStatus ticketStatus;

    @Column(name = "purchase_at", nullable = false, unique = false)
    private LocalDateTime purchasedAt;

    @Column(name = "scanned_at")
    private LocalDateTime scannedAt;

    @PrePersist
    protected void prePersist() {
        purchasedAt = LocalDateTime.now();
    }
}
