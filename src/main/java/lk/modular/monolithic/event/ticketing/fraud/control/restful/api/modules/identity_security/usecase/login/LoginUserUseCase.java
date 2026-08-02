package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login;
;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUserResult;

public interface LoginUserUseCase {

    //login user
    AuthenticatedUserResult login(String username, String password);
}
