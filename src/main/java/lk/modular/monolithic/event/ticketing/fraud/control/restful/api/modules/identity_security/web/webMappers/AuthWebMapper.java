package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.webMappers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.AuthResponseDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.LoginRequestDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs.RegisterRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

    /* requestDTO to domain model */

    //__REGISTER__
    User registerDomainModel(RegisterRequestDTO registerRequestDTO);

    //__LOGIN__
    User loginDomainModel(LoginRequestDTO loginRequestDTO);

    /* domain model to responseDTO */

    //domain model to responseDTO
    AuthResponseDTO authResponseDTO(User user);

    /* Authenticated User Result to ResponseDTO */
    AuthResponseDTO toAuthResponseDTO(AuthenticatedUserResult authenticatedUserResult);






}
