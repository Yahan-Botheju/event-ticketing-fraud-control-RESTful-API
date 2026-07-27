package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.jpa;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    //find user by email
    Optional<UserEntity> findByEmail(String email);
}
