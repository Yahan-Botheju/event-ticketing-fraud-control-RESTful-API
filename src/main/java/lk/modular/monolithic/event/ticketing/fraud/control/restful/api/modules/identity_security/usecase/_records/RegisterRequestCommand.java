package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase._records;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.Role;

public record RegisterRequestCommand(
        String fullName,
        String email,
        String password,
        Role role
) {
}
