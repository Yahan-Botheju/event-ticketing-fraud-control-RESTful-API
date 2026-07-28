package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories;

public interface JwtTokenProvider {
    //generate access token
    String generateAccessToken(Long userId, String email, String role);

    //generate refresh token
    String generateRefreshToken(String email);
}
