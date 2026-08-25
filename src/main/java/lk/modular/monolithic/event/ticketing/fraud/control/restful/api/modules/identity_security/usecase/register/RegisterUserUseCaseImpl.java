package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.Role;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase._records.RegisterRequestCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void register(RegisterRequestCommand registerCommand) {
        if(userRepository.existsByEmail(registerCommand.email())) {
            throw new ConflictException("Email already registered..!!");
        }

        //encode password using  identity domain repo
        String encodedPassword = identityProvider.encodePassword(registerCommand.password());

        //if role is empty set as default role
        Role userRole = registerCommand.role() != null ? registerCommand.role() : Role.ATTENDEE;

        //create user object with required details
        User newUser = User.registerNewUser(
                registerCommand.fullName(),
                registerCommand.email(),
                encodedPassword,
                registerCommand.role() == null ? userRole : registerCommand.role());

        //save user
        userRepository.registerUser(newUser);
    }
}
