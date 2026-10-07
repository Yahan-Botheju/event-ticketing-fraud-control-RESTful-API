package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.Role;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records.RegisterUserResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.MethodArgumentNotValidException;

import java.time.LocalDateTime;

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
    public RegisterUserResult register(RegisterUserCommand registerUserCommand) {

        //check incoming fields
        if (registerUserCommand.fullName().isEmpty()
                || registerUserCommand.email().isEmpty()
                || registerUserCommand.password().isEmpty()
                || !registerUserCommand.role().isEmpty()
        ) {
            throw new MethodArgumentNotValidException("Required fields cannot be missing!!");
        }

        //check username existence
        if (userRepository.existsByEmail(registerUserCommand.email())) {
            throw new ConflictException("Email already registered..!!");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        //create new user model through domain
        User registerNewUser = User.createNewUser(
                registerUserCommand.fullName(),
                registerUserCommand.email(),
                identityProvider.encodePassword(registerUserCommand.password()),
                Role.ATTENDEE,
                currentTime
        );

        User registeredUser = userRepository.registerUser(registerNewUser);

        return new RegisterUserResult(
                registeredUser.getUserId(),
                registeredUser.getFullName(),
                registeredUser.getEmail(),
                registeredUser.getRole().toString(),
                registeredUser.getCreatedAt()
        );
    }
}
