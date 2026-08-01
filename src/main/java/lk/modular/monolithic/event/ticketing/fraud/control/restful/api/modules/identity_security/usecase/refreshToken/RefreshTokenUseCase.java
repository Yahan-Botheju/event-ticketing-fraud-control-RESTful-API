package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.records.AuthenticatedUserResult;

public interface RefreshTokenUseCase {

    //active new access token when its expired
    AuthenticatedUserResult execute(String refreshToken);
}
