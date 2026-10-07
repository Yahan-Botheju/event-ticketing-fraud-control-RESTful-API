package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserResult;

public interface RegisterUserUseCase {

    //register user
    RegisterUserResult register(RegisterUserCommand registerUserCommand);

}
