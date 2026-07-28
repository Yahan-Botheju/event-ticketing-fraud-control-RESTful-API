package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories;

import io.jsonwebtoken.Claims;

public interface JwtTokenProvider {
    //generate access token
    String generateAccessToken(Long userId, String email, String role);

    //generate refresh token
    String generateRefreshToken(String email);

    //get claim from token
    Claims getClaimsFromToken(String token);

    //get email from token
    String getEmailFromToken(String token);

    //validate token
    boolean validateToken(String token);

    //get refresh token expiration
    long getRefreshTokenExpiry();
}
