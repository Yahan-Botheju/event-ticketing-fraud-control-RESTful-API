package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories;

import java.util.Optional;

public interface RedisTokenRepository {

    //save refresh token in redis context
    void saveRefreshToken(Long userId, String refreshToken, long durationInMs);

    //get refresh token from redis context related to userId
    Optional<String> getRefreshToken(Long userId);
}
