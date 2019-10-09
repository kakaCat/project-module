package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.utils.redis.redisson.lock.DistributedLocker;
import project.utils.redis.redisson.repo.RedisStringRepo;
import project.utils.redis.redisson.repo.impl.RedisStringRepository;
import project.utils.redis.redisson.utils.RedisUtil;
import test.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = RedissonApplication.class,
        properties = {
                "spring.redis.redisson.config=classpath:redisson.yaml",
                "spring.redis.timeout=10000",
                "spring.cache.type=redis"
        })
public class RedissonUtilTest {

    @Autowired
    private DistributedLocker locker;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redisson;


    @Test
    public void testApp() {
        User user= new User();
        user.setAge("11");
        user.setUsername("aa");
        redisUtil.set("aa:b:c",user);
    }


    @Test
    public void setTime() {
        User user= new User();
        user.setAge("11");
        user.setUsername("aa");
        redisUtil.set("aa.aa",user,10);
    }

    @Test
    public void model(){
        User user= new User();
        user.setAge("11");
        user.setUsername("aa");
        RedisStringRepo redisStringRepo = new RedisStringRepository(redisson);

        redisStringRepo.saveBean("user:username", user);

    }


}
