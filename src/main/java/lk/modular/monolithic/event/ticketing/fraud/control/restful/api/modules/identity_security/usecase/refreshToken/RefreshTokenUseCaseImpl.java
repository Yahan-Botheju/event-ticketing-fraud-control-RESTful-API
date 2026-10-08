package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records.RefreshTokenCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records.RefreshTokenResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.MethodArgumentNotValidException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ResourceNotFoundException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.UnauthorizedException;

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
    public RefreshTokenResult execute(RefreshTokenCommand refreshTokenCommand) {
        //check incoming field
        if(refreshTokenCommand.refreshToken().isEmpty() || refreshTokenCommand.refreshToken().isBlank()){
            throw new MethodArgumentNotValidException("The refresh token cannot be empty");
        }

        //check token valid or not
        if(!jwtTokenProvider.validateToken(refreshTokenCommand.refreshToken())) {
            throw new UnauthorizedException("Invalid or expired refresh token..!!");
        }
        //get email from token
        String email = jwtTokenProvider.getEmailFromToken(refreshTokenCommand.refreshToken());

        //check user existence
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found..!!"));

        //WHITELIST_CHECK check token is available as active session in redis context
        String activateToken = redisTokenRepository.getRefreshToken(existingUser.getUserId())
                .orElseThrow(() -> new UnauthorizedException("Session expired..!!"));

        //check tokens are same
        if(!activateToken.equals(refreshTokenCommand.refreshToken())) {
            throw new UnauthorizedException("Token mismatch or revoked..!!");
        }

        /* __GENERATE_NEW_ACCESS_TOKEN__ */

        //new access_token
        String newAccessToken = jwtTokenProvider.generateAccessToken(
                existingUser.getUserId(),
                existingUser.getEmail(),
                existingUser.getRole().name());

        //new refresh_token
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(
                existingUser.getUserId(),
                existingUser.getEmail(),
                existingUser.getRole().name());

        //override from new refresh_token (TOKEN ROTATION) override new token
        redisTokenRepository.saveRefreshToken(
                existingUser.getUserId(),
                newRefreshToken,
                jwtTokenProvider.getRefreshTokenExpiry()
        );

        return new RefreshTokenResult(
                newAccessToken,
                newRefreshToken,
                existingUser.getUserId(),
                existingUser.getEmail(),
                existingUser.getRole().name()
        );
    }
}
