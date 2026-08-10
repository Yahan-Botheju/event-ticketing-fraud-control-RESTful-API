package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.shared_domain.SharedRepositories;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;

import java.util.Optional;

public interface UserValidationClientRepository {

    //user find by id
    Optional<User> userValidateById(Long userId);
}
