package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.DTOs;

import java.time.LocalDateTime;

public record ErrorDetails(
        int status,
        String message,
        String description,
        LocalDateTime timestamp
) {
}
