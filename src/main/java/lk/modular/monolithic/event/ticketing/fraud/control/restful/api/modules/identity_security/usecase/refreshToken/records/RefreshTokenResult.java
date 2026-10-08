package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records;

public record RefreshTokenResult(
        String refreshToken,
        String accessToken,
        Long userId,
        String email,
        String role
) {
}
