package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.AuthResponseDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.RegisterUserRequestDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.RegisterUserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

    /* __REGISTER__ */

    //request to command
    RegisterUserCommand toRegisterUserCommand(RegisterUserRequestDTO registerUserRequestDTO);

    //domain model to response
    RegisterUserResponseDTO toRegisterUserResponseDTO(RegisterUserResult registerUserResult);


    /* domain model to responseDTO */

    //domain model to responseDTO
    AuthResponseDTO authResponseDTO(User user);

    /* Authenticated User Result to ResponseDTO */
    AuthResponseDTO toAuthResponseDTO(AuthenticatedUserResult authenticatedUserResult);

}
