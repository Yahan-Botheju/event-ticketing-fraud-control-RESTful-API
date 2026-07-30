package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.auth;

import jakarta.transaction.Transactional;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.Role;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.ConflictException;

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

    //register user
    @Override
    @Transactional
    public void registerUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new ConflictException("Email already registered..!!");
        }

        //encode password using  identity domain repo
        String encodedPassword = identityProvider.encode(user.getPassword());

        //if role is empty set as default role
        Role userRole = user.getRole() != null ? user.getRole() : Role.ATTENDEE;


    }
}
