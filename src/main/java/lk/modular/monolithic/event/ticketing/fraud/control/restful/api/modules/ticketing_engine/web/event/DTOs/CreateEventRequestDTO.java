package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDTO {
    @NotBlank(message = "Event title cannot be empty")
    private String eventTitle;

    private String eventDescription;

    @NotBlank(message = "Event Location cannot be empty")
    private String eventLocation;

    @NotNull(message = "Event date cannot be empty")
    @Future(message = "Event date should be in future")
    private LocalDateTime eventDate;

    @NotNull(message = "Total ticket count is required")
    @Min(value = 1, message = "Ticket total cannot be less than one ticket")
    private Integer eventTotalTickets;

    @NotNull(message = "Event ticket price is required")
    private BigDecimal eventTicketPrice;


}
