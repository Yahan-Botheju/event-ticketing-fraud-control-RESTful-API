package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.shared_domain.SharedRepositories;


public interface UserValidationClientRepository {

    //user find by id
    boolean userValidateById(Long userId);
}
