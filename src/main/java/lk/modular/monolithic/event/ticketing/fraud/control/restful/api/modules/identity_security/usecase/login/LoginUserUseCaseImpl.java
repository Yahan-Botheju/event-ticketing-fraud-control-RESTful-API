package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUser;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records.LoginUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records.LoginUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.InvalidCredentialsException;

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
    public LoginUserResult login(LoginUserCommand loginUserCommand) {

        if (loginUserCommand.email().isEmpty()
                || loginUserCommand.email().isBlank()
                || loginUserCommand.password().isEmpty()
                || loginUserCommand.password().isBlank()
        ) {
            throw new InvalidCredentialsException("Email and password cannot be missing");
        }

        //authenticate user
        AuthenticatedUser authenticatedUser = identityProvider
                .authenticateUser(loginUserCommand.email(), loginUserCommand.password());

        /* __GENERATE_TOKENS__ */

        //access token
        String accessToken = jwtTokenProvider.generateAccessToken(
                authenticatedUser.userId(),
                authenticatedUser.email(),
                authenticatedUser.role()
        );
        //refresh token
        String refreshToken = jwtTokenProvider.generateRefreshToken(
                authenticatedUser.userId(),
                authenticatedUser.email(),
                authenticatedUser.role()
        );

        /* __STATEFUL_WHITELISTING__ */
        //save refresh token redis context
        redisTokenRepository.saveRefreshToken(
                authenticatedUser.userId(),
                refreshToken,
                jwtTokenProvider.getRefreshTokenExpiry()

        );

        return new LoginUserResult(
                accessToken,
                refreshToken,
                authenticatedUser.userId(),
                authenticatedUser.email(),
                authenticatedUser.role()
        );
    }


}
