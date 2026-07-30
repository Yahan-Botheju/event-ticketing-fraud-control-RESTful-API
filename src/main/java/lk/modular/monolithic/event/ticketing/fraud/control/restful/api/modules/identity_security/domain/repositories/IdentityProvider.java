package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories;

public interface IdentityProvider {
    String encode(String rawPassword);
}
