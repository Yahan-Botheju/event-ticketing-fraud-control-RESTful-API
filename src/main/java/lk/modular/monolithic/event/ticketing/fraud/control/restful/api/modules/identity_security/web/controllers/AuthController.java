package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.controllers;

import jakarta.validation.Valid;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.LoginUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout.LogoutUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.RefreshTokenUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.RegisterUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.RegisterRequestDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.webMappers.AuthWebMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.DTOs.ApiResponse;
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
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequestDTO registerRequestDTO
            ){
        User domainModel = authWebMapper.registerDomainModel(registerRequestDTO);
        registerUserUseCase.register(domainModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
