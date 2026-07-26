package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.jpa;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
