package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.InvalidTicketException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.ResourceNotFoundException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.UnauthorizedException;

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
        //check token valid or not
        if(!jwtTokenProvider.validateToken(refreshToken)) {
            throw new InvalidTicketException("Invalid or expired refresh token..!!");
        }
        //get email from token
        String email = jwtTokenProvider.getEmailFromToken(refreshToken);

        //check user existence
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found..!!"));

        //WHITELIST_CHECK check token is available as active session in redis context
        String activateToken = redisTokenRepository.getRefreshToken(existingUser.getUserId())
                .orElseThrow(() -> new UnauthorizedException("Session expired..!!"));

        //check tokens are same
        if(!activateToken.equals(refreshToken)) {
            throw new InvalidTicketException("Token mismatch or revoked..!!");
        }

        /* __GENERATE_NEW_TOKENS__ */

        //access_token
        String newAccessToken = jwtTokenProvider.generateAccessToken(
                existingUser.getUserId(),
                existingUser.getEmail(),
                existingUser.getRole().toString()
                );

        //refresh_token
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(newAccessToken);


    }
}
