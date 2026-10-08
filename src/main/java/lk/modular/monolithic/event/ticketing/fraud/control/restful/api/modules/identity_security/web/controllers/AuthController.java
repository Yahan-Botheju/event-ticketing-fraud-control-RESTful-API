package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.controllers;

import jakarta.validation.Valid;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.LoginUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records.LoginUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records.LoginUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout.LogoutUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.RefreshTokenUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records.RefreshTokenCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records.RefreshTokenResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.RegisterUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.web_resolver.annotation.CurrentUserId;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.webMappers.AuthWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //inject required dependencies
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUserUseCase logoutUserUseCase;
    private final AuthWebMapper authWebMapper;

    public AuthController(
            RegisterUserUseCase registerUserUseCase,
            LoginUserUseCase loginUserUseCase,
            RefreshTokenUseCase refreshTokenUseCase,
            LogoutUserUseCase logoutUserUseCase,
            AuthWebMapper authWebMapper
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.logoutUserUseCase = logoutUserUseCase;
        this.authWebMapper = authWebMapper;
    }

    //register endpoint
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDTO> register(
            @Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO
    ){
        RegisterUserCommand toCommand = authWebMapper.toRegisterUserCommand(registerUserRequestDTO);
        RegisterUserResult toRegisterResult = registerUserUseCase.register(toCommand);
        RegisterUserResponseDTO responseDTO = authWebMapper.toRegisterUserResponseDTO(toRegisterResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //login endpoint
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> login(
            @Valid @RequestBody LoginUserRequestDTO loginUserRequestDTO
    ){

        LoginUserCommand toCommand = authWebMapper.toLoginUserCommand(loginUserRequestDTO);
        LoginUserResult toLoginResult = loginUserUseCase.login(toCommand);
        LoginUserResponseDTO responseDTO = authWebMapper.toLoginUserResponseDTO(toLoginResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //refresh-token endpoint
    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(
            @Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO
    ){
        RefreshTokenCommand toCommand = authWebMapper.toRefreshTokenCommand(refreshTokenRequestDTO);
        RefreshTokenResult tokenResult = refreshTokenUseCase.execute(toCommand);
        RefreshTokenResponseDTO responseDTO = authWebMapper.toRefreshTokenResponseDTO(tokenResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @CurrentUserId Long userId
    ){
        logoutUserUseCase.execute(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
