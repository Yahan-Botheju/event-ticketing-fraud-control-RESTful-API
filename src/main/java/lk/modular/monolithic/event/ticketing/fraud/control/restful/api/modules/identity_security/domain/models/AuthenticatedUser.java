package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models;

public record AuthenticatedUser(
        String userId,
        String email,
        String role
) {}
