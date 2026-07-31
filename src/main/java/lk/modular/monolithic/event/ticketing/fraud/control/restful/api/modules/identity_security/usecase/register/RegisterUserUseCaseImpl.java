package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.Role;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.ConflictException;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    //inject required dependencies
    private final IdentityProvider identityProvider;
    private final UserRepository userRepository;


    public RegisterUserUseCaseImpl(
            IdentityProvider identityProvider,
            UserRepository userRepository
    ) {
        this.identityProvider = identityProvider;
        this.userRepository = userRepository;
    }

    //register user
    @Override
    public void register(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new ConflictException("Email already registered..!!");
        }

        //encode password using  identity domain repo
        String encodedPassword = identityProvider.encode(user.getPassword());

        //if role is empty set as default role
        Role userRole = user.getRole() != null ? user.getRole() : Role.ATTENDEE;

        //create user object with required details
        User newUser = User.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .password(encodedPassword)
                .role(userRole)
                .build();

        //save user
        userRepository.registerUser(newUser);
    }
}
