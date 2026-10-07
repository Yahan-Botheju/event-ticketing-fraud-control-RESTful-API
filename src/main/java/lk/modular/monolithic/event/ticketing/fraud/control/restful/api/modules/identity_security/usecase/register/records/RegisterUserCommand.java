package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.records;

public record RegisterUserCommand(
        String fullName,
        String email,
        String password,
        String role
) {
}
