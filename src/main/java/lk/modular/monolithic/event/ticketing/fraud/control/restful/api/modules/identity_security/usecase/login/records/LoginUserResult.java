package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records;

public record LoginUserResult(
        String accessToken,
        String refreshToken,
        Long userId,
        String email,
        String role
) {
}
