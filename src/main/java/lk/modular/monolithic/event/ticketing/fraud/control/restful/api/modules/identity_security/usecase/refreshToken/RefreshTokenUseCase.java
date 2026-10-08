package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records.RefreshTokenCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.refreshToken.records.RefreshTokenResult;

public interface RefreshTokenUseCase {

    //active new access token when its expired
    RefreshTokenResult execute(RefreshTokenCommand refreshTokenCommand);
}
