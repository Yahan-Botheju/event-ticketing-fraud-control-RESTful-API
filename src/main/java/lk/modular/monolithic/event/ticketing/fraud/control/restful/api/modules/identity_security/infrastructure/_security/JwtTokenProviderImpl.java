package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;

import javax.crypto.SecretKey;

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
}
