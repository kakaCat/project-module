package project.dal.inventory.time.api;


import project.dal.inventory.time.entity.TimeInventoryLockModel;

import java.util.List;

/**
 * @author fangfeng
 * @since 2018/3/30
 */
public interface TimeInventoryLockService {

    /**
     * 获得时间库存锁记录对象
     * @param lockId
     * @return
     */
    TimeInventoryLockModel getTimeInventoryLock(long lockId);

    /**
     * 获得时间库存锁记录对象
     * @param lockScope
     * @param lockScopeId
     * @return
     */
    List<TimeInventoryLockModel> getTimeInventoryLocks(Integer lockScope, String lockScopeId);

    /**
     * 获取时间库存锁记录对象
     * @param lockScope
     * @param lockScopeId
     * @param state
     * @return
     */
    List<TimeInventoryLockModel> getTimeInventoryLocks(Integer lockScope, String lockScopeId, Integer state);

    /**
     * 获取时间库存锁记录对象
     * @param query
     * @return
     */
//    PagedList<TimeInventoryLockModel> getTimeInventoryLocks(TimeInventoryLockQuery query);

    /**
     * 根据时间库存锁记录id更新锁记录
     *
     * 适用场景:
     *      1. 先约时间后产生订单号的订单, 在成功生成订单号之后必须调用本方法更新 lockScope & lockScopeId
     *         <b>
     *             其中 {@link TimeInventoryLockModel#id}
     *                 {@link TimeInventoryLockModel#lockScope}
     *                 {@link TimeInventoryLockModel#lockScopeId}
     *             必填
     *         </b>
     *
     *      2. 其它场景...
     *
     * @param inventoryLock
     */
    boolean updateTimeInventoryLock(TimeInventoryLockModel inventoryLock);
}
