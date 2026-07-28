package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.InvalidTicketException;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenProviderImpl implements JwtTokenProvider {

    //inject required dependencies
    private final SecretKey secretKey;
    private final long accessTokenExpirationMs;
    private final long refreshTokenExpirationMs;

    public JwtTokenProviderImpl(
            SecretKey secretKey,
            long accessTokenExpirationMs,
            long refreshTokenExpirationMs
    ) {
        this.secretKey = secretKey;
        this.accessTokenExpirationMs = accessTokenExpirationMs;
        this.refreshTokenExpirationMs = refreshTokenExpirationMs;
    }


    /* __TOKEN_GENERATION__ */


    //generate access token
    @Override
    public String generateAccessToken(Long userId, String email, String role) {
         return Jwts.builder()
                 .subject(email)
                 .claim("userId", userId)
                 .claim("role", role)
                 .issuedAt(new Date(System.currentTimeMillis()))
                 .expiration(new Date(System.currentTimeMillis() + accessTokenExpirationMs))
                 .signWith(secretKey)
                 .compact();
    }

    //generate refresh token
    @Override
    public String generateRefreshToken(String email) {
         return Jwts.builder()
                 .subject(email)
                 .issuedAt(new Date(System.currentTimeMillis()))
                 .expiration(new Date((System.currentTimeMillis() + refreshTokenExpirationMs)))
                 .signWith(secretKey)
                 .compact();
    }


    /* __TOKEN_EXTRACTION_METHOD__ */


    //get claims from token
    @Override
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //get email from token
    @Override
    public String getEmailFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }


    /* __VALIDATION_METHODS__ */

    //validate token
    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (JwtException | InvalidTicketException e){
            return false;
        }
    }

    //refresh token expiration
    @Override
    public long getRefreshTokenExpiry() {
        return refreshTokenExpirationMs;
    }


}
