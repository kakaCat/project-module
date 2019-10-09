package project.utils.redis.redisson.repo;

public interface RedisStringRepo {

    /**
     * 保存复杂类型数据到缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean saveBean(String key, Object value);


    /**
     * 保存复杂类型数据到缓存（并设置失效时间）
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    boolean saveBeanExp(String key, Object value, int seconds);


    /**
     * 取得复杂类型数据
     *
     * @param key
     * @return
     */
    Object getBean(String key);


    /**
     * 将自增变量存入缓存
     */
    boolean saveSeq(String key, long seqNo);

    /**
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET
     * if Not eXists』(如果不存在，则 SET)的简写。 <br>
     * 保存成功，返回 true <br>
     * 保存失败，返回 false
     */
    boolean saveNX(String key, String val);


    /**
     * 从缓存中删除数据
     *
     * @param key
     * @return
     */
    boolean delKey(String key);

}
