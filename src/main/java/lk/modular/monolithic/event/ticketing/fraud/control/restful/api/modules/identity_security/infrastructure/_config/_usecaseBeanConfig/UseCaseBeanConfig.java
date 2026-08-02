package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._config._usecaseBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.LoginUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.LoginUserUseCaseImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout.LogoutUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout.LogoutUserUseCaseImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.RefreshTokenUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.RefreshTokenUseCaseImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.RegisterUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.RegisterUserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class UseCaseBeanConfig {

    //register usecase bean config
    @Bean
    public RegisterUserUseCase authenticateUserUseCase(
            IdentityProvider identityProvider,
            UserRepository userRepository
    ) {
        return new RegisterUserUseCaseImpl(identityProvider, userRepository);
    }

    //login usecase bean config
    @Bean
    public LoginUserUseCase loginUserUseCase(
            IdentityProvider identityProvider,
            JwtTokenProvider jwtTokenProvider,
            RedisTokenRepository redisTokenRepository
    ) {
        return new LoginUserUseCaseImpl(identityProvider, jwtTokenProvider,redisTokenRepository);
    }

    //refresh token usecase bean config
    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(
            UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider,
            RedisTokenRepository redisTokenRepository
    ){
        return new RefreshTokenUseCaseImpl(userRepository,jwtTokenProvider,redisTokenRepository);
    }

    //logout usecase bean config
    @Bean
    public LogoutUserUseCase logoutUserUseCase(
            RedisTokenRepository redisTokenRepository
    ){
        return new LogoutUserUseCaseImpl(redisTokenRepository);
    }
}
