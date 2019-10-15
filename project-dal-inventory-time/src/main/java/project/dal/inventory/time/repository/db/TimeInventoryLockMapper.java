package project.dal.inventory.time.repository.db;


import org.apache.ibatis.annotations.Param;
import project.dal.inventory.time.entity.TimeInventoryLockModel;
import project.dal.inventory.time.query.TimeInventoryLockQuery;

import java.util.Date;
import java.util.List;


public interface TimeInventoryLockMapper {

    /**
     * 创建锁时间记录
     * @param lock
     * @return
     */
    int createTimeInventoryLock(TimeInventoryLockModel lock);

    /**
     * 获取时间库存锁记录
     * @param id
     * @return
     */
    TimeInventoryLockModel getTimeInventoryLock(Long id);

    /**
     * 获取时间库存锁记录
     * @param lockQuery
     * @return
     */
    List<TimeInventoryLockModel> getTimeInventoryLocks(TimeInventoryLockQuery lockQuery);

    /**
     * 获取时间库存锁记录数量
     * @param lockQuery
     * @return
     */
    int countTimeInventoryLocks(TimeInventoryLockQuery lockQuery);

    /**
     * 获取时间库存锁记录
     * @param lockScope
     * @param lockScopeId
     * @param state
     * @return
     */
    List<TimeInventoryLockModel> getTimeInventoryLocksByLockScope(@Param("lockScope") Integer lockScope,
                                                                  @Param("lockScopeId") String lockScopeId,
                                                                  @Param("state") Integer state);

    /**
     * 获取相应订单的锁记录次数
     * @param lockScope
     * @param lockScopeId
     * @return
     */
    int countTimeInventoryLocksByLockScope(@Param("lockScope") int lockScope, @Param("lockScopeId") String lockScopeId);

    /**
     * 获取时间库存锁记录(由scope及[startDate, endDate) 进行限定)
     * @param scope
     * @param scopeId
     * @param state
     * @param startDate
     * @param endDate
     * @return
     */
    List<TimeInventoryLockModel> getTimeInventoryLocksByScopeAndDates(@Param("scope") Integer scope,
                                                                      @Param("scopeId") String scopeId,
                                                                      @Param("state") Integer state,
                                                                      @Param("startDate") Date startDate,
                                                                      @Param("endDate") Date endDate);

    /**
     * 更新时间库存锁记录
     * @param lock
     * @return
     */
    int updateTimeInventoryLock(TimeInventoryLockModel lock);

    /**
     * 关闭锁时间记录
     * @param lock
     * @return
     */
    int releaseTimeInventoryLock(TimeInventoryLockModel lock);
}
