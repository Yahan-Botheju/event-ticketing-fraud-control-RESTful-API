package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records;

public record AuthenticatedUserResult(
        String accessToken,
        String refreshToken,
        Long userId,
        String email,
        String role
) {
}
