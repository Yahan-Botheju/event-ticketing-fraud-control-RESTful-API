package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;

public class LoginUserUseCaseImpl implements LoginUserUseCase {

    //inject required dependencies
    private final IdentityProvider identityProvider;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenRepository redisTokenRepository;

    public LoginUserUseCaseImpl(
            IdentityProvider identityProvider,
            JwtTokenProvider jwtTokenProvider,
            RedisTokenRepository redisTokenRepository
    ) {
        this.identityProvider = identityProvider;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTokenRepository = redisTokenRepository;
    }
}
