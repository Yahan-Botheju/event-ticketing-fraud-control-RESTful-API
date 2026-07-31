package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories;

public interface IdentityProvider {
    //shared password encoder
    String encodePassword(String rawPassword);

    //shared authentication operations
    Object authenticate(String username, String password);
}
