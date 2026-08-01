package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records;

public record AuthenticatedUser(
        Long userId,
        String email,
        String role
) {}
