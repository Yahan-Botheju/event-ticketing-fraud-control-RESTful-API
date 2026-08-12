package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.shared_domain_usage;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.jpa.JpaUserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.persistenceMapper.UserPersistenceMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.shared_domain.SharedRepositories.UserValidationClientRepository;

public class UserValidationClientRepositoryImpl implements UserValidationClientRepository {

    //inject required dependencies
    private final JpaUserRepository jpaUserRepository;


    public UserValidationClientRepositoryImpl(
            JpaUserRepository jpaUserRepository
    ) {
        this.jpaUserRepository = jpaUserRepository;
    }

    //check user existence for TICKETING_ENGINE module
    /*
     * Interface / port available in -> shared/shared_domain/SharedRepositories/
     */

    //user find by id
    @Override
    public boolean userValidateById(Long userId){
        return jpaUserRepository.existsById(userId);
    }
}
