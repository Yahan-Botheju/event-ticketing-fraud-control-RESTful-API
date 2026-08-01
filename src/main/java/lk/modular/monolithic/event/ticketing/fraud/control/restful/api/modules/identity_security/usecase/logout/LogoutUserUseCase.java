package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout;

public interface LogoutUserUseCase {

    //initiate logout user
    void execute(Long userId);
}
