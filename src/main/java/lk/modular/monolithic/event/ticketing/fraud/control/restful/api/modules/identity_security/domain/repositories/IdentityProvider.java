package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUser;

public interface IdentityProvider {
    //shared password encoder
    String encodePassword(String rawPassword);

    //shared authentication operations
    AuthenticatedUser authenticateUser(String username, String password);
}
