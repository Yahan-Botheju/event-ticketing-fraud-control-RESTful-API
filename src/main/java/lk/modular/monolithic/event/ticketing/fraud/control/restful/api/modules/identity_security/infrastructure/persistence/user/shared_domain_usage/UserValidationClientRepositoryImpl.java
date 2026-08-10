package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.shared_domain_usage;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.jpa.JpaUserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.persistenceMapper.UserPersistenceMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.shared_domain.SharedRepositories.UserValidationClientRepository;

public class UserValidationClientRepositoryImpl implements UserValidationClientRepository {

    //inject required dependencies
    private final JpaUserRepository jpaUserRepository;
    private final UserPersistenceMapper userPersistenceMapper;


    public UserValidationClientRepositoryImpl(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.jpaUserRepository = jpaUserRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }
}
