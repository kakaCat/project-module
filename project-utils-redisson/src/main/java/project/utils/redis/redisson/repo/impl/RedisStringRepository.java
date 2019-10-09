package project.utils.redis.redisson.repo.impl;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import project.utils.redis.redisson.repo.RedisStringRepo;


public class RedisStringRepository implements RedisStringRepo {

    private static Logger log = LoggerFactory.getLogger(RedisStringRepository.class);

    public RedisStringRepository(RedissonClient redisson){
        this.redisson = redisson;
    }

    private RedissonClient redisson;

    @Override
    public boolean saveBean(String key, Object value) {
        try {
            RBucket<Object> keyObject = redisson.getBucket(key);
            keyObject.set(value);
            return true;
        } catch (Throwable t) {
            log.error("cache[" + key + "] faile, value[" + value + "]", t);
        }

        return false;
    }

    @Override
    public boolean saveBeanExp(String key, Object value, int seconds) {
        return false;
    }

    @Override
    public Object getBean(String key) {
        return null;
    }

    @Override
    public boolean saveSeq(String key, long seqNo) {
        return false;
    }

    @Override
    public boolean saveNX(String key, String val) {
        return false;
    }

    @Override
    public boolean delKey(String key) {
        return false;
    }
}
