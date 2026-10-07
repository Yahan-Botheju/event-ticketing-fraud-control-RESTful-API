package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records.LoginUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.login.records.LoginUserResult;

public interface LoginUserUseCase {

    //login user
    LoginUserResult login(LoginUserCommand loginUserCommand);
}
