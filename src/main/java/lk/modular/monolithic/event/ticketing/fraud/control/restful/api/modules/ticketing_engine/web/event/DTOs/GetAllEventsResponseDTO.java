package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEventsResponseDTO {
    private Long eventId;
    private String eventTitle;
    private String eventDescription;
    private String eventLocation;
    private String eventDate;
    private String eventTicketPrice;
}
