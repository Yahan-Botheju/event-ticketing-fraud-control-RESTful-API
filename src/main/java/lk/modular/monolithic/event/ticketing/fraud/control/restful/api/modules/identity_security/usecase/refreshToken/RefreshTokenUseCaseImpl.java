package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;

public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenRepository redisTokenRepository;


    public RefreshTokenUseCaseImpl(
            UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider,
            RedisTokenRepository redisTokenRepository
    ) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTokenRepository = redisTokenRepository;
    }

    //active new access token when its expired
    @Override
    public AuthenticatedUserResult execute(String refreshToken) {
        return null;
    }
}
