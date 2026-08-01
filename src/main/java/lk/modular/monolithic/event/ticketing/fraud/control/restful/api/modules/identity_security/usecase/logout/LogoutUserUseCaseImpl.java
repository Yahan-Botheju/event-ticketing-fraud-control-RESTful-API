package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.logout;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;

public class LogoutUserUseCaseImpl implements  LogoutUserUseCase {

    //inject required dependencies
    private final RedisTokenRepository redisTokenRepository;

    public LogoutUserUseCaseImpl(RedisTokenRepository redisTokenRepository) {
        this.redisTokenRepository = redisTokenRepository;
    }
}
