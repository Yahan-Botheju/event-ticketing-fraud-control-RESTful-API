package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories;

public interface RedisLockService {

    //get distributed lock
    boolean acquireLock(String lockKey, String lockValue, long expireTimeInSeconds);

    //release lock
    boolean releaseLock(String lockKey, String lockValue);
}
