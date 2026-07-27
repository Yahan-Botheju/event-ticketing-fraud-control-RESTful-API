package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    //find user by email
    Optional<User> findByEmail(String email);
}
