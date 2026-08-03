package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.persistence.event.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "events", schema = "ticketing_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_title", nullable = false)
    private String eventTitle;

    @Column(name = "event_description", columnDefinition = "TEXT")
    private String eventDescription;

    @Column(name = "event_location", nullable = false)
    private String eventLocation;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "event_total_tickets", nullable = false)
    private Integer eventTotalTickets;

    @Column(name = "event_available_tickets", nullable = false)
    private Integer eventAvailableTickets;

    @Column(name = "event_ticket_price", nullable = false)
    private BigDecimal eventTicketPrice;

    @Column(name = "organizer_id", nullable = false)
    private Long organizerId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
