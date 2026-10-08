package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout.records.LogoutCommand;

public interface LogoutUserUseCase {

    //initiate logout user
    void execute(LogoutCommand logoutCommand);
}
