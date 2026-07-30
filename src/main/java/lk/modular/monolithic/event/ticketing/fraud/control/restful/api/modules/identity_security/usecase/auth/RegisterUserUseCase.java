package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.auth;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;

public interface RegisterUserUseCase {

    //register user
    void registerUser(User user);
}
