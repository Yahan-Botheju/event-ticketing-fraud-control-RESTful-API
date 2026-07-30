package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.auth;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;

public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    //inject required dependencies
    private final IdentityProvider identityProvider;
    private final UserRepository userRepository;


    public AuthenticateUserUseCaseImpl(
            IdentityProvider identityProvider,
            UserRepository userRepository
    ) {
        this.identityProvider = identityProvider;
        this.userRepository = userRepository;
    }
}
