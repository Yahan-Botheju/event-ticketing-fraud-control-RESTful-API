package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.entities.UserEntity;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.jpa.JpaUserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.persistenceMapper.UserPersistenceMapper;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    //inject required dependencies
    private final JpaUserRepository jpaUserRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    public UserRepositoryImpl(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.jpaUserRepository = jpaUserRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }


    /*  __HELPER_METHODS__ */


    //user find by ID
    @Override
    public Optional<User> findById(Long userId) {
        return jpaUserRepository.findById(userId).map(userPersistenceMapper::toDomainModel);
    }

    //find user by email
    @Override
    public Optional<User> findByEmail(String email){
        return jpaUserRepository.findByEmail(email).map(userPersistenceMapper::toDomainModel);
    }

    //check user email existence
    @Override
    public boolean existsByEmail(String email){
        return jpaUserRepository.existsByEmail(email);
    }


    /*  __PUBLIC_METHODS__ */

    //register user
    @Override
    public User registerUser(User user) {
        UserEntity toEntity = userPersistenceMapper.toEntity(user);
        UserEntity registeredUser =  jpaUserRepository.save(toEntity);

        return userPersistenceMapper.toDomainModel(registeredUser);
    }
}
