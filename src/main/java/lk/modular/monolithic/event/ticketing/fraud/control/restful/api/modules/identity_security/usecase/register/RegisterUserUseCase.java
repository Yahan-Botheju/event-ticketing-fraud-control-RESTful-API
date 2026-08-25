package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase._records.RegisterRequestCommand;

public interface RegisterUserUseCase {

    //register user
    void register(RegisterRequestCommand registerCommand);

}
