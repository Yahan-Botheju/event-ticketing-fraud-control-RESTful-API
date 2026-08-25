package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase._records;

public record LoginRequestCommand(
        String email,
        String password
) {
}
