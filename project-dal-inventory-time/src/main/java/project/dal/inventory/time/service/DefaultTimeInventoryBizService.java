//package project.dal.inventory.time.service;
//
//import com.helijia.common.api.util.PagedList;
//import com.helijia.pivot.inventory.api.TimeInventoryBizService;
//import com.helijia.pivot.inventory.api.TimeInventoryService;
//import com.helijia.pivot.inventory.api.TimeInventoryViewConverter;
//import com.helijia.pivot.inventory.common.util.Assert;
//import com.helijia.pivot.inventory.common.util.DateUtil;
//import com.helijia.pivot.inventory.common.util.ErrorCode;
//import com.helijia.pivot.inventory.common.util.PositionsConverter;
//import com.helijia.pivot.inventory.dao.mapper.TimeInventoryLockMapper;
//import com.helijia.pivot.inventory.dao.mapper.TimeInventoryMapper;
//import com.helijia.pivot.inventory.dao.mapper.TimeInventoryOpMapper;
//import com.helijia.pivot.inventory.model.*;
//import com.helijia.pivot.inventory.param.*;
//import com.helijia.pivot.inventory.query.TimeInventoryOpQuery;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import java.util.*;
//
///**
// * @author fangfeng
// * @since 2018/3/29
// */
//
//public class DefaultTimeInventoryBizService implements TimeInventoryBizService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTimeInventoryBizService.class);
//
//    @Autowired
//    private TimeInventoryMapper timeInventoryMapper;
//    @Autowired
//    private TimeInventoryLockMapper timeInventoryLockMapper;
//    @Autowired
//    private TimeInventoryOpMapper timeInventoryOpMapper;
//    @Autowired
//    private TimeInventoryService inventoryService;
//    @Autowired
//    private TimeInventoryViewConverter converter;
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
//    public void lockAndReleaseTimeInventories(TimeInventoryLockParam lockParam, TimeInventoryReleaseParam releaseParam) {
//        int count = timeInventoryLockMapper.countTimeInventoryLocksByLockScope(lockParam.getLockScope(), lockParam.getLockScopeId());
//        /**
//         * 需要考虑的点包括:
//         *  1. 可能需要支持不同类目不同的改约次数限制
//         *
//         * 目前需要保证用户最多改约三次，即一个订单号最多允许存在 4 个 Lock 操作(下单后的预约算一次Lock)
//         */
//        if (lockParam.getRepeatLockTimes() != TimeInventoryLockParam.INFINITE_REPEAT_LOCK_TIMES && count > lockParam.getRepeatLockTimes()) {
//            LOGGER.warn("execute lock and release failed : {}, {}",lockParam.toString(), releaseParam.toString());
//            throw ErrorCode.buildException(ErrorCode.LOCK_AND_RELEASE_FAILED);
//        }
//        Assert.notNull(lockParam.getLockScope(), "TimeInventoryLockParam.lockScope");
//        Assert.notNull(lockParam.getLockScopeId(), "TimeInventoryLockParam.lockScopeId");
//        Assert.notNull(releaseParam.getLockScope(), "TimeInventoryReleaseParam.lockScope");
//        Assert.notNull(releaseParam.getLockScopeId(), "TimeInventoryReleaseParam.lockScopeId");
//        Assert.equals(lockParam.getLockScope(), releaseParam.getLockScope(), "Lock Scope");
//        Assert.equals(lockParam.getLockScopeId(), releaseParam.getLockScopeId(), "Lock Scope Id");
//        // 释放老的已约时间库存
//        inventoryService.releaseTimeInventories(releaseParam);
//        // 锁定新的时间库存
//        inventoryService.lockTimeInventories(lockParam);
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
//    public void disableWholeDayTimeInventories(TimeInventoryDisableParam disableParam) {
//        Assert.notNull(disableParam, "TimeInventoryDisableParam");
//        Assert.notNull(disableParam.getItems(), "TimeInventoryDisableParam.items");
//        Assert.notNull(disableParam.getScope(), "TimeInventoryDisableParam.scope");
//        Assert.notNull(disableParam.getScopeId(), "TimeInventoryDisableParam.scopeId");
//        Assert.notNull(disableParam.getUnit(), "TimeInventoryDisableParam.unit");
//        List<Date> dates = disableParam.getDates();
//
//        // get TimeInventories from DB
//        List<TimeInventoryModel> inventories = timeInventoryMapper.getTimeInventoriesBySeparateDates(disableParam.getScope(), disableParam.getScopeId(), dates);
//
//        // 统一配置来源
//        disableParam.setOpSource(TimeInventoryOpModel.OP_SOURCE_SHIFT);
//
//        // Judge if Inventory units is unused
//        List<TimeInventoryViewModel> views = converter.convert(inventories);
//        for (TimeInventoryViewModel view : views) {
//            List<TimeInventoryItemModel> items = new ArrayList<>(); // itemList to reSet states;
//            Assert.equals(disableParam.getUnit(), view.getInventory().getUnit(), "TIME UNIT");
//            for (TimeInventoryItemModel item : view.getItems()) {
//                if (item.getState() == TimeInventoryModel.STATE_UNUSED) {
//                    items.add(item);
//                } else if (item.getState() == TimeInventoryModel.STATE_USED) {
//                    throw ErrorCode.buildException(ErrorCode.OP_FAILED, "cause STATE_USED exist");
//                }
//            }
//            disableParam.setItems(items);
//            inventoryService.disableTimeInventories(disableParam);
//        }
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
//    public boolean enableWholeDayTimeInventories(TimeInventoryEnableParam param) {
//        Assert.notNull(param, "TimeInventoryEnableParam");
//        Assert.notNull(param.getItems(), "TimeInventoryEnableParam.items");
//        Assert.notNull(param.getScope(), "TimeInventoryEnableParam.scope");
//        Assert.notNull(param.getScopeId(), "TimeInventoryEnableParam.scopeId");
//        Assert.notNull(param.getUnit(), "TimeInventoryEnableParam.unit");
//        List<Date> dates = param.getDates();
//
//        for (Date date : dates) {
//            TimeInventoryOpModel inventoryOp = timeInventoryOpMapper.getLastTimeInventoryOpByScopeAndDate(param.getScope(), param.getScopeId(), date);
//            if (inventoryOp == null || param.getUnit() != inventoryOp.getUnit()) {
//                return false;
//            }
//
//            // FIXME: 一期临时支持，基于下面的理由。
//            // Assert.notNull(inventoryOp, "InventoryOpModel");
//            // Assert.equals(param.getUnit(), inventoryOp.getUnit(), "TIME UNIT");
//            if (inventoryOp.getState() != TimeInventoryModel.STATE_DISABLED) {
//                return false;
//                // FIXME: 一期由于不改造客户端，为实现全天休息逻辑，临时支持，下同 @fangfeng @2018-04-17
//                // throw ErrorCode.buildException(ErrorCode.OP_FAILED, "recovery failetd");
//            }
//            if (inventoryOp.getOpSource() != TimeInventoryOpModel.OP_SOURCE_SHIFT) {
//                return false;
//                //throw ErrorCode.buildException(ErrorCode.OP_FAILED, "recovery failed");
//            }
//
//            List<TimeInventoryItemModel> items = PositionsConverter.decodeOpPositions(inventoryOp.getDate(), inventoryOp.getPositions(), inventoryOp.getUnit());
//
//            TimeInventoryListParam listParam = new TimeInventoryListParam();
//            listParam.setScope(param.getScope());
//            listParam.setScopeId(param.getScopeId());
//            listParam.setStartDate(date);
//            listParam.setEndDate(DateUtil.nextDate(date, 1));
//            TimeInventoryModel inventory = inventoryService.listTimeInventories(listParam).get(0);
//            TimeInventoryViewModel view = converter.convert(inventory);
//
//            for (TimeInventoryItemModel item : view.getItems()) {
//                // see help.helijia.com issue ID 11597
//                if (item.getState() == TimeInventoryModel.STATE_USED) {
//                    return false;
//                }
//            }
//
//            param.setItems(items);
//            inventoryService.enableTimeInventories(param);
//        }
//
//        return true;
//    }
//
//    @Override
//    public List<TimeInventoryViewModel> batchListTimeInventories(int scope, List<String> scopeIds, Date startDate, Date endDate) {
//        List<TimeInventoryModel> inventories = batchList(scope, scopeIds, startDate, endDate);
//        if (CollectionUtils.isEmpty(inventories)) {
//            return null;
//        }
//        // init basic lock param with lock policy
//        TimeInventoryLockParam lockParam = new TimeInventoryLockParam();
//        lockParam.setPolicy(TimeInventoryLockParam.POLICY_HOME_ORIGIN);
//        lockParam.setAmount(120);
//        lockParam.setUnit(TimeInventoryModel.UNIT_HALF);
//        lockParam.setConvertUMS(false);
//        lockParam.setConvertWithRelatedInventories(false);
//
//        List<TimeInventoryViewModel> views = new ArrayList<>(inventories.size());
//        /**
//         * 以属于同一领域对象的时间库存对象为一组, 进行附带锁策略的加工
//         * 其中
//         * 1. 不考虑该时间库存对象列表时间段以外的时间对首尾时间库存项的影响(单次SQL太耗时，且影响不大)
//         * 2. 不考虑加工【已约-里程碑】{@link TimeInventoryViewModel#STATE_USED_MILESTONE} 状态
//         */
//        List<TimeInventoryModel> singleInventories = new ArrayList<>();
//        int baseScope = inventories.get(0).getScope();
//        String baseScopeId = inventories.get(0).getScopeId();
//        for (final TimeInventoryModel inventory : inventories) {
//            if (baseScope == inventory.getScope() && baseScopeId.equals(inventory.getScopeId())) {
//                singleInventories.add(inventory);
//            } else {
//                // 使用内存排序，不通过 MySQL 进行
//                Collections.sort(singleInventories, new Comparator<TimeInventoryModel>() {
//                    @Override
//                    public int compare(TimeInventoryModel o1, TimeInventoryModel o2) {
//                        return o1.getDate().compareTo(o2.getDate());
//                    }
//                });
//                // FIXME: 临时逻辑，暂时用【关闭】不影响时间库存的锁策略加工法，后续需要切换 @fangfeng @2018-06-05
//                views.addAll(converter.convert(singleInventories, lockParam, false));
//                singleInventories = new ArrayList<TimeInventoryModel>() {{
//                    add(inventory);
//                }};
//                baseScope = inventory.getScope();
//                baseScopeId = inventory.getScopeId();
//            }
//        }
//        // 使用内存排序，不通过 MySQL 进行
//        Collections.sort(singleInventories, new Comparator<TimeInventoryModel>() {
//            @Override
//            public int compare(TimeInventoryModel o1, TimeInventoryModel o2) {
//                return o1.getDate().compareTo(o2.getDate());
//            }
//        });
//        // FIXME: 临时逻辑，暂时用【关闭】不影响时间库存的锁策略加工法，后续需要切换 @fangfeng @2018-06-05
//        views.addAll(converter.convert(singleInventories, lockParam, false));
//
//        return views;
//    }
//
//    @Override
//    public List<TimeInventoryViewModel> batchListAndConvertTimeInventories(int scope, List<String> scopeIds, Date startDate, Date endDate, TimeInventoryLockParam lockParam) {
//        // 前置 Assert, 避免非法参数下仍执行耗时SQL
//        Assert.notNull(lockParam, "lockParam");
//
//        List<TimeInventoryModel> inventories = batchList(scope, scopeIds, startDate, endDate);
//        if (CollectionUtils.isEmpty(inventories)) {
//            return null;
//        }
//        List<TimeInventoryViewModel> views = new ArrayList<>(inventories.size());
//
//        List<TimeInventoryModel> singleInventories = new ArrayList<>();
//        int baseScope = inventories.get(0).getScope();
//        String baseScopeId = inventories.get(0).getScopeId();
//        for (final TimeInventoryModel inventory : inventories) {
//            if (baseScope == inventory.getScope() && baseScopeId.equals(inventory.getScopeId())) {
//                singleInventories.add(inventory);
//            } else {
//                Collections.sort(singleInventories, new Comparator<TimeInventoryModel>() {
//                    @Override
//                    public int compare(TimeInventoryModel o1, TimeInventoryModel o2) {
//                        return o1.getDate().compareTo(o2.getDate());
//                    }
//                });
//                lockParam.setScope(baseScope);
//                lockParam.setScopeId(baseScopeId);
//                views.addAll(converter.convert(singleInventories, lockParam));
//                singleInventories = new ArrayList<TimeInventoryModel>() {{
//                    add(inventory);
//                }};
//                baseScope = inventory.getScope();
//                baseScopeId = inventory.getScopeId();
//            }
//        }
//        lockParam.setScope(baseScope);
//        lockParam.setScopeId(baseScopeId);
//        views.addAll(converter.convert(singleInventories, lockParam));
//
//        return views;
//    }
//
//    @Override
//    public List<TimeInventoryModel> batchList(int scope, List<String> scopeIds, Date startDate, Date endDate) {
//        Assert.notNull(scopeIds, "batchList.scopeIds");
//        Assert.notNull(startDate, "batchList.startDate");
//        Assert.notNull(endDate, "batchList.endDate");
//        long start = System.currentTimeMillis();
//        final List<TimeInventoryModel> inventories = timeInventoryMapper.batchGetTimeInventoriesByDates(scope, scopeIds, startDate, endDate);
//        long end = System.currentTimeMillis();
//        for (String scopeId : scopeIds) {
//            LOGGER.info("scopeId{} ",scopeId );
//        }
//
//
//
//        LOGGER.info("execute batchList, totally used : {}ms", end-start);
//        return inventories;
//    }
//
//    @Override
//    public PagedList<TimeInventoryOpModel> listTimeInventoryOps(TimeInventoryOpQuery opQuery) {
//        List<TimeInventoryOpModel> inventoryOps = timeInventoryOpMapper.listTimeInventoryOps(opQuery);
//        for (TimeInventoryOpModel inventoryOp : inventoryOps) {
//            inventoryOp.setItems(PositionsConverter.decodeOpPositions(inventoryOp.getDate(), inventoryOp.getPositions(), inventoryOp.getUnit()));
//        }
//        int count = timeInventoryOpMapper.countTimeInventoryOps(opQuery);
//        PagedList<TimeInventoryOpModel> pagedOps = new PagedList<TimeInventoryOpModel>(opQuery.getItemsPerPage(), count, opQuery.getPage());
//        pagedOps.setData(inventoryOps);
//        return pagedOps;
//    }
//}
