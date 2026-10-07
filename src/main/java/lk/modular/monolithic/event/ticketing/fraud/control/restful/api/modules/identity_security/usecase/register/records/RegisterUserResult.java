package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records;

import java.time.LocalDateTime;

public record RegisterUserResult(
        Long userId,
        String fullName,
        String email,
        String role,
        LocalDateTime createdAt
) {
}
