package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records.LoginUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records.LoginUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout.records.LogoutCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records.RefreshTokenCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records.RefreshTokenResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

    /* __REGISTER__ */

    //request to command
    RegisterUserCommand toRegisterUserCommand(RegisterUserRequestDTO registerUserRequestDTO);

    //domain model to response
    RegisterUserResponseDTO toRegisterUserResponseDTO(RegisterUserResult registerUserResult);


    /* __LOGIN__ */

    //request to command
    LoginUserCommand toLoginUserCommand(LoginUserRequestDTO loginUserRequestDTO);

    //domain model to response
    LoginUserResponseDTO toLoginUserResponseDTO(LoginUserResult loginUserResult);


    /* __REFRESH_TOKEN__ */

    //request to command
    RefreshTokenCommand  toRefreshTokenCommand(RefreshTokenRequestDTO refreshTokenRequestDTO);

    //domain model to response
    RefreshTokenResponseDTO toRefreshTokenResponseDTO(RefreshTokenResult refreshTokenResult);

    /* __LOGOUT__ */

    //request to command
    LogoutCommand toLogoutCommand(Long userId);

}
