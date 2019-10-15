//package project.dal.inventory.time.service;
//
//import java.util.*;
//
//import com.helijia.common.api.util.PagedList;
//import com.helijia.pivot.inventory.common.util.DateUtil;
//import com.helijia.pivot.inventory.common.util.ErrorCode;
//import com.helijia.pivot.inventory.common.util.PositionsConverter;
//import com.helijia.pivot.inventory.dao.mapper.TimeInventoryMapper;
//import com.helijia.pivot.inventory.model.InternalTimeInventoryModel;
//import com.helijia.pivot.inventory.model.TimeInventoryItemModel;
//import com.helijia.pivot.inventory.model.TimeInventoryModel;
//import com.helijia.pivot.inventory.param.TimeInventoryLockParam;
//import com.helijia.pivot.inventory.query.TimeInventoryLockQuery;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.helijia.pivot.inventory.api.TimeInventoryLockService;
//import com.helijia.pivot.inventory.dao.mapper.TimeInventoryLockMapper;
//import com.helijia.pivot.inventory.model.TimeInventoryLockModel;
//import org.springframework.util.CollectionUtils;
//
///**
// * @author fangfeng
// * @since 2018/3/30
// */
//
//public class DefaultTimeInventoryLockService implements TimeInventoryLockService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTimeInventoryLockService.class);
//
//    @Autowired
//    private TimeInventoryMapper inventoryMapper;
//    @Autowired
//    private TimeInventoryLockMapper inventoryLockMapper;
//
//    @Override
//    public TimeInventoryLockModel getTimeInventoryLock(long lockId) {
//        return inventoryLockMapper.getTimeInventoryLock(lockId);
//    }
//
//    @Override
//    public List<TimeInventoryLockModel> getTimeInventoryLocks(Integer lockScope, String lockScopeId) {
//        List<TimeInventoryLockModel> inventoryLocks = inventoryLockMapper.getTimeInventoryLocksByLockScope(lockScope, lockScopeId, null);
//        if (inventoryLocks.size() == 30) {
//            // FIXME: inventoryLocks 查询限制，现有条件下绝对不会超过 3 条记录，为防止命中记录过多，加以限制 @fangfeng @2018-04-11
//            // 如果 warning 过多，需要改造成分页的形式
//            LOGGER.warn("more time inventory locks exist, but currently only display 30 locks");
//        }
//        return inventoryLocks;
//    }
//
//    @Override
//    public List<TimeInventoryLockModel> getTimeInventoryLocks(Integer lockScope, String lockScopeId, Integer state) {
//        List<TimeInventoryLockModel> inventoryLocks = inventoryLockMapper.getTimeInventoryLocksByLockScope(lockScope, lockScopeId, state);
//        return inventoryLocks;
//    }
//
//    @Override
//    public PagedList<TimeInventoryLockModel> getTimeInventoryLocks(TimeInventoryLockQuery query) {
//        List<TimeInventoryLockModel> locks = inventoryLockMapper.getTimeInventoryLocks(query);
//        for (TimeInventoryLockModel lock : locks) {
//            lock.setItems(PositionsConverter.decodeLockPositions(lock.getDate(), lock.getPositions(), lock.getUnit()));
//        }
//        int count = inventoryLockMapper.countTimeInventoryLocks(query);
//        PagedList<TimeInventoryLockModel> pagedLocks = new PagedList<>(query.getItemsPerPage(), count, query.getPage());
//        pagedLocks.setData(locks);
//        return pagedLocks;
//    }
//
//    @Override
//    public boolean updateTimeInventoryLock(TimeInventoryLockModel inventoryLock) {
//        int count = inventoryLockMapper.updateTimeInventoryLock(inventoryLock);
//        return count == 1 ? true : false;
//    }
//
//    /**
//     * 插入 or 更新时间库存锁记录
//     *
//     * <p>
//     * 根据
//     *  param.lockScope,
//     *  param.lockScopeId,
//     *  param.scope,
//     *  param.scopeId,
//     *  state=STATE_ON
//     * 查询 inventory_time_lock 表记录，
//     *  - 如果存在，则执行更新操作
//     *  - 如果不存在，则执行插入操作
//     *
//     * <p>
//     * 目标:
//     *  - 保证 state=STATE_ON 的记录中, (lockScope, lockScopeId, scope, scopeId) 保持 unique
//     * </p>
//     * @param lockParam
//     * @return
//     */
//    public TimeInventoryLockModel insertOrUpdateTimeInventoryLock(TimeInventoryLockParam lockParam) {
//        List<Date> dates = lockParam.getDates();
//        if (!CollectionUtils.isEmpty(dates)) {
//            // FIXME: 临时逻辑，支持 【关闭】-> 【已约】的状态恢复 @fangfeng @2018-05-23
//            List<TimeInventoryModel> inventories = inventoryMapper.getTimeInventoriesBySeparateDates(
//                    lockParam.getScope(), lockParam.getScopeId(), lockParam.getDates());
//
//            if (CollectionUtils.isEmpty(inventories) || inventories.size() != dates.size()) {
//                LOGGER.warn("time inventory not exist : {}", lockParam.toString());
//                throw ErrorCode.buildException(ErrorCode.INVENTORY_NOT_EXIST);
//            }
//
//            Map<Date, InternalTimeInventoryModel> mappings = new HashMap<>();
//            for (TimeInventoryModel inventory : inventories) {
//                InternalTimeInventoryModel internal = InternalTimeInventoryModel.parse(inventory);
//                mappings.put(internal.getDate(), internal);
//            }
//
//            for (TimeInventoryItemModel item : lockParam.getItems()) {
//                InternalTimeInventoryModel internal = mappings.get(DateUtil.toDate(item.getDate()));
//                item.setState(internal.getState(item.getPosition()));
//            }
//        }
//
//        // 预查询所有记录, 限定 (lockScope, lockScopeId, STATE_ON)
//        List<TimeInventoryLockModel> inventoryLocks = inventoryLockMapper.getTimeInventoryLocksByLockScope(
//                lockParam.getLockScope(), lockParam.getLockScopeId(), TimeInventoryLockModel.STATE_ON
//        );
//
//        // 限定 (scope, scopeId) 查询，存在就走 update
//        for (TimeInventoryLockModel inventoryLock : inventoryLocks) {
//            if (inventoryLock.getScope().equals(lockParam.getScope()) && inventoryLock.getScopeId().equals(lockParam.getScopeId())) {
//                return updateTimeInventoryLock(inventoryLock, lockParam);
//            }
//        }
//
//        // 否则走 insert
//        return insertTimeInventoryLock(lockParam);
//    }
//
//    private TimeInventoryLockModel insertTimeInventoryLock(TimeInventoryLockParam lockParam) {
//        TimeInventoryLockModel lock = new TimeInventoryLockModel();
//        lock.setScope(lockParam.getScope());
//        lock.setScopeId(lockParam.getScopeId());
//        lock.setUnit(lockParam.getUnit());
//        lock.setDate(lockParam.getItem().getDate());
//        lock.setState(TimeInventoryLockModel.STATE_ON);
//        lock.setPositions(PositionsConverter.encodeLockPositions(lockParam.getItem().getDate(), lockParam.getItems(), lockParam.getUnit()));
//
//        // 存在 lockScope 的场景
//        if (lockParam.getLockScope() != null) {
//            lock.setLockScope(lockParam.getLockScope());
//            lock.setLockScopeId(lockParam.getLockScopeId());
//        }
//
//        try {
//            inventoryLockMapper.createTimeInventoryLock(lock);
//        } catch (RuntimeException e) {
//            LOGGER.error("db insert time inventory lock failed : {}", lockParam.toString());
//            throw ErrorCode.buildException(ErrorCode.OP_FAILED, "create inventory lock failed");
//        }
//        return lock;
//    }
//
//    private TimeInventoryLockModel updateTimeInventoryLock(TimeInventoryLockModel inventoryLock, TimeInventoryLockParam lockParam) {
//        // 之前已经锁了的库存
//        List<TimeInventoryItemModel> items = PositionsConverter.decodeLockPositions(
//                inventoryLock.getDate(), inventoryLock.getPositions(), inventoryLock.getUnit()
//        );
//
//        // 现在需要额外锁的库存
//        items.addAll(lockParam.getItems());
//        inventoryLock.setPositions(PositionsConverter.encodeLockPositions(inventoryLock.getDate(), items, inventoryLock.getUnit()));
//
//        inventoryLockMapper.updateTimeInventoryLock(inventoryLock);
//        return inventoryLock;
//    }
//}
