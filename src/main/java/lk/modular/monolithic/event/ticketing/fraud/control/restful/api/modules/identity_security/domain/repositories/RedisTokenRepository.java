package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories;

public interface RedisTokenRepository {

    //save refresh token in redis context
    void saveRefreshToken(Long userId, String refreshToken, long durationInMs);
}
