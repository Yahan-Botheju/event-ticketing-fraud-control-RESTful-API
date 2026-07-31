package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login;

import jakarta.servlet.http.HttpServletResponse;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.AuthenticatedUser;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.AuthenticatedUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;

public interface LoginUserUseCase {

    //login user
    AuthenticatedUserResult login(String username, String password, HttpServletResponse httpServletResponse);
}
