package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.persistenceMapper;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models.User;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure.persistence.user.entities.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    //domain model to entity
    UserEntity toEntity(User user);

    //entity to domain model
    User toDomainModel(UserEntity userEntity);
}
