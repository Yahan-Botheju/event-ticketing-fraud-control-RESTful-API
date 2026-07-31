package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login;

import jakarta.servlet.http.HttpServletResponse;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.AuthenticatedUser;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.AuthenticatedUserResult;
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

    //login user
    @Override
    public AuthenticatedUserResult login(
            String username,
            String password,
            HttpServletResponse httpServletResponse
    ) {
        //authenticate user
        AuthenticatedUser authenticatedUser  = identityProvider.authenticateUser(username, password);

        /* __GENERATE_TOKENS__ */

        //access token
        String accessToken = jwtTokenProvider.generateAccessToken(
                authenticatedUser.userId(),
                authenticatedUser.email(),
                authenticatedUser.role()
        );

        //refresh token
        String refreshToken = jwtTokenProvider.generateRefreshToken(authenticatedUser.email());


    }
}
