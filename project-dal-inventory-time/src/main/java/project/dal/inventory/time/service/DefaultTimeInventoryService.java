package project.dal.inventory.time.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import project.dal.inventory.time.api.TimeInventoryService;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.param.DalParam;
import project.dal.inventory.time.param.TimeInventoryCreateParam;
import project.dal.inventory.time.param.TimeInventoryEnableParam;
import project.dal.inventory.time.policy.Policyfactory;
import project.dal.inventory.time.policy.TimeInventoryCreatePolicy;
import project.dal.inventory.time.repository.db.TimeInventoryMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jinli Mar 3, 2018
 */

public class DefaultTimeInventoryService implements TimeInventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTimeInventoryService.class);


    @Autowired
    private TimeInventoryMapper timeInventoryMapper;

    @Autowired
    private Policyfactory policyfactory;

    @Override
    public List<TimeInventoryModel> createTimeInventories(DalParam dalParam) {
        TimeInventoryCreateParam param = (TimeInventoryCreateParam)dalParam;
        // judge before create if DB already exist some inventories.
//        Long isExist = timeInventoryMapper.testTimeInventoryByDates(param);
//        if (isExist != null) {
//            LOGGER.warn("time inventories already exist : {}", param.toString());
//            throw ErrorCode.buildException(ErrorCode.INVENTORY_ALREADY_EXIST);
//        }

        List<TimeInventoryModel> inventories = new ArrayList<>();
        // get create policy
        TimeInventoryCreatePolicy policy = policyfactory.getCreatePolicy(param.getPolicy());
        if (policy == null) {
            LOGGER.warn("cannot find create policy - required policyId : {}", param.getPolicy());
            return null;
        }

        for (Date date : param.getDates()) {
            // get init values for creation
            TimeInventoryCreatePolicy.TimeInventoryCreateValues values = policy.create(param);

            TimeInventoryModel inventory = new TimeInventoryModel();
            inventory.setScope(param.getScope());
            inventory.setScopeId(param.getScopeId());
            inventory.setTimeUnit(param.getTimeUnit());
            inventory.setInventoryTime(date);
            inventory.setValue0(values.value0);
            inventory.setValue1(values.value1);
            inventory.setValue2(values.value2);
            inventory.setValue3(values.value3);
            inventory.setValue4(values.value4);
            inventory.setValue5(values.value5);

            inventories.add(inventory);
        }

        // get current insert columns
        try {
            timeInventoryMapper.insertTimeInventories(inventories);
        } catch (RuntimeException e) {
            LOGGER.error("insert time inventory failed : {}, caused by : {}", param.toString(), e.getMessage());
//            throw ErrorCode.buildException(ErrorCode.OP_FAILED);
        }
        return inventories;
    }

    @Override
    public List<TimeInventoryModel> listTimeInventories(DalParam param) {
        return null;
    }

//    @Autowired
//    private TimeInventoryLockMapper timeInventoryLockMapper;
//
//    @Autowired
//    private TimeInventoryOpMapper timeInventoryOpMapper;
//
//    @Autowired
//    private DefaultTimeInventoryLockService timeInventoryLockService;


//    @Override
//    public List<TimeInventoryModel> createTimeInventories(TimeInventoryCreateParam param) {
//        Assert.notNull(param, "TimeInventoryCreateParam");
//        // param.scopeId cannot be null
//        Assert.notNull(param.getScopeId(), "TimeInventoryCreateParam.scopeId");
//
//        // judge before create if DB already exist some inventories.
//        Long isExist = timeInventoryMapper.testTimeInventoryByDates(param);
//        if (isExist != null) {
//            LOGGER.warn("time inventories already exist : {}", param.toString());
//            throw ErrorCode.buildException(ErrorCode.INVENTORY_ALREADY_EXIST);
//        }
//
//        List<TimeInventoryModel> inventories = new ArrayList<>();
//        // get create policy
//        TimeInventoryCreatePolicy policy = createPolicies.get(param.getPolicy());
//        if (policy == null) {
//            LOGGER.warn("cannot find create policy - required policyId : {}", param.getPolicy());
//            throw ErrorCode.buildException(ErrorCode.CANNOT_FIND_POLICY, "CreatePolicy", param.getPolicy());
//        }
//
//        for (Date date : param.getDates()) {
//            // get init values for creation
//            TimeInventoryCreateValues values = policy.create(param);
//
//            TimeInventoryModel inventory = new TimeInventoryModel();
//            inventory.setScope(param.getScope());
//            inventory.setScopeId(param.getScopeId());
//            inventory.setUnit(param.getUnit());
//            inventory.setDate(date);
//            inventory.setValue0(values.value0);
//            inventory.setValue1(values.value1);
//            inventory.setValue2(values.value2);
//            inventory.setValue3(values.value3);
//            inventory.setValue4(values.value4);
//            inventory.setValue5(values.value5);
//
//            inventories.add(inventory);
//        }
//
//        // get current insert columns
//        try {
//            timeInventoryMapper.insertTimeInventories(inventories);
//        } catch (RuntimeException e) {
//            LOGGER.error("insert time inventory failed : {}, caused by : {}", param.toString(), e.getMessage());
//            throw ErrorCode.buildException(ErrorCode.OP_FAILED);
//        }
//        return inventories;
//    }
//
//    @Override
//    public List<TimeInventoryModel> listTimeInventories(TimeInventoryListParam param) {
//        Assert.notNull(param, "TimeInventoryListParam");
//        Assert.notNull(param.getScopeId(), "TimeInventoryListParam.scopeId");
//        Assert.notNull(param.getStartDate(), "TimeInventoryListParam.startDate");
//        Assert.notNull(param.getEndDate(), "TimeInventoryListParam.endDate");
//        List<TimeInventoryModel> inventories = timeInventoryMapper.getTimeInventoriesByDates(param);
//        if (CollectionUtils.isEmpty(inventories)) {
//            LOGGER.info("cannot find time inventories : {}", param.toString());
//            throw ErrorCode.buildException(ErrorCode.INVENTORY_NOT_EXIST);
//        }
//        TimeInventoryUtil.checkUnits(inventories);
//        return inventories;
//    }
//
//    @Override
//    public void legalizeTimeInventories(TimeInventoryLegalizeParam param) {
//        Set<Integer> targetStateSet = TimeInventoryLegalizeParam.getTargetStateSet();
//        for (TimeInventoryItemModel item : param.getItems()) {
//            if (!targetStateSet.contains(item.getState())) {
//                LOGGER.warn("legalizedTimeInventories(TimeInventoryLegalizedParam) cannot set state=%d. param : {}", item.getState(), param);
//                throw ErrorCode.buildException(ErrorCode.OP_FAILED, String.format("legalizedTimeInventories(...) cannot set state=%d.", item.getState()));
//            }
//        }
//        setTimeInventoryStates(param, getAllowedStates(TimeInventoryModel.STATE_INVALID));
//    }
//
//    @Override
//    public void illegalizeTimeInventories(TimeInventoryIllegalizedParam param) {
//        Set<Integer> targetStateSet = TimeInventoryIllegalizedParam.getTargetStateSet();
//        for (TimeInventoryItemModel item : param.getItems()) {
//            if (!targetStateSet.contains(item.getState())) {
//                LOGGER.warn("illegalizedTimeInventories(TimeInventoryIllegalizedParam) cannot set state=%d. param : {}", item.getState(), param);
//                throw ErrorCode.buildException(ErrorCode.OP_FAILED, String.format("illegalizedTimeInventories(...) cannot set state=%d.", item.getState()));
//            }
//        }
//        setTimeInventoryStates(param, getAllowedStates(TimeInventoryModel.STATE_DISABLED, TimeInventoryModel.STATE_UNUSED));
//    }

//    @Override
//    public void enableTimeInventories(TimeInventoryEnableParam param) {
//        // FIXME: 临时支持 后续请删除
//        for (TimeInventoryItemModel item : param.getItems()) {
//            item.setState(param.state());
//        }
//
//        setTimeInventoryStates(param, getAllowedStates(TimeInventoryModel.STATE_DISABLED));
//    }

//    @Override
//    public void disableTimeInventories(TimeInventoryDisableParam param) {
//        // FIXME: 临时支持 后续请删除 @fangfeng @2018-05-23
//        for (TimeInventoryItemModel item : param.getItems()) {
//            item.setState(param.state());
//        }
//
//        setTimeInventoryStates(param, getAllowedStates(TimeInventoryModel.STATE_UNUSED));
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
//    public TimeInventoryLockModel lockTimeInventories(TimeInventoryLockParam param) {
//        Assert.notNull(param, "TimeInventoryLockParam");
//
//        // get lock policy
//        TimeInventoryLockPolicy policy;
//        policy = lockPolicies.get(param.getPolicy());
//        if (policy == null) {
//            LOGGER.warn("cannot find lock policy - required policyId : {}", param.getPolicy());
//            throw ErrorCode.buildException(ErrorCode.CANNOT_FIND_POLICY, "LockPolicy", param.getPolicy());
//        }
//        TimeInventoryLockValues values = policy.lock(param);
//
//        Calendar cal;
//        int position;
//        List<TimeInventoryItemModel> items = new ArrayList<>();
//
//        // 获取根据 lock 策略锁定的 param.items
//        Assert.notNull(param.getItem(), "TimeInventoryLockParam.item");
//
//        Assert.notNull(param.getItem().getDate(), "TimeInventoryLockParam.item.date");
//        // 往前约
//        cal = Calendar.getInstance();
//        cal.setTime(param.getItem().getDate());
//        position = param.getItem().getPosition();
//        for (int i = 0; i < values.previous; i++) {
//            cal.add(Calendar.MINUTE, -InternalTimeInventoryModel.getUnitMinutes(param.getUnit()));
//            if (--position < 0) {
//                position = InternalTimeInventoryModel.getPositionLength(param.getUnit()) - 1;
//            }
//            items.add(0, new TimeInventoryItemModel(position, cal.getTime()));
//        }
//
//        // 往后约(包括当前的时间点)
//        cal = Calendar.getInstance();
//        cal.setTime(param.getItem().getDate());
//        position = param.getItem().getPosition();
//        for (int i = 0; i < values.following; i++) {
//            items.add(items.size(), new TimeInventoryItemModel(position, cal.getTime()));
//            if (++position == InternalTimeInventoryModel.getPositionLength(param.getUnit())) {
//                position = 0;
//            }
//            cal.add(Calendar.MINUTE, InternalTimeInventoryModel.getUnitMinutes(param.getUnit()));
//        }
//
//        // 如果需要锁的最早的一个时间单元已经过去(相较于真实时间)，则锁时间库存的方法抛出异常
//        if (!CollectionUtils.isEmpty(items) && items.get(0).getDate().before(new Date())) {
//            LOGGER.warn("lock time inventory failed - time already passed : {}", param.toString());
//            throw ErrorCode.buildException(ErrorCode.OP_FAILED, "time already passed");
//        }
//
//        param.setItems(items);
//        TimeInventoryLockModel lock = timeInventoryLockService.insertOrUpdateTimeInventoryLock(param);
//
//        for (TimeInventoryItemModel item : items) {
//            item.setState(param.state());
//        }
//
//        // FIXME: 临时支持 【关闭】 -> 【已约】 @fangfeng @2018-05-23
//        setTimeInventoryStates(param, getAllowedStates(TimeInventoryModel.STATE_UNUSED, TimeInventoryModel.STATE_DISABLED));
//        return lock;
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
//    public List<TimeInventoryLockModel> lockTimeInventories(List<TimeInventoryLockParam> lockParams) {
//        List<TimeInventoryLockModel> locks = new ArrayList<>();
//        for (TimeInventoryLockParam lockParam : lockParams) {
//            locks.add(lockTimeInventories(lockParam));
//        }
//        return locks;
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
//    public void releaseTimeInventories(final TimeInventoryReleaseParam param) {
//        releaseTimeInventories(new ArrayList<TimeInventoryReleaseParam>() {{
//            add(param);
//        }});
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
//    public void releaseTimeInventories(List<TimeInventoryReleaseParam> params) {
//        // params 不能为空
//        if (CollectionUtils.isEmpty(params)) {
//            throw ErrorCode.buildException(ErrorCode.OP_FAILED, "ReleaseParam List is Empty");
//        }
//
//        // 要求批量的 lockScope & lockScopeId 一致
//        BaseTimeInventoryLockOpParam lockOpParam = params.get(0);
//        for (TimeInventoryReleaseParam param : params) {
//            Assert.equals(param.getLockScope(), lockOpParam.getLockScope(), "ReleaseParam.lockScope");
//            Assert.equals(param.getLockScopeId(), lockOpParam.getLockScopeId(), "ReleaseParam.lockScopeId");
//        }
//
//        List<TimeInventoryLockModel> locks = timeInventoryLockMapper.getTimeInventoryLocksByLockScope(lockOpParam.getLockScope(), lockOpParam.getLockScopeId(), TimeInventoryLockModel.STATE_ON);
//
//        // 查询不到目标记录
//        if (CollectionUtils.isEmpty(locks)) {
//            LOGGER.error("time inventory released failed - no \'state_on\' record : {}", params.toString());
//            throw ErrorCode.buildException(ErrorCode.OP_FAILED, "release failed");
//        }
//
//        Map<ScopeElement, Integer> standardScopeMap = new TreeMap<>(new Comparator<ScopeElement>() {
//            @Override
//            public int compare(ScopeElement o1, ScopeElement o2) {
//                if (o1.scope.compareTo(o2.scope) == 0) {
//                    return o1.scopeId.compareTo(o2.scopeId);
//                } else {
//                    return o1.scope.compareTo(o2.scope);
//                }
//            }
//        });
//        for (int i=0;i<params.size();i++) {
//            standardScopeMap.put(new ScopeElement(params.get(i).getScope(), params.get(i).getScopeId()), i);
//        }
//
//        for (TimeInventoryLockModel lock : locks) {
//            Integer index = standardScopeMap.get(new ScopeElement(lock.getScope(), lock.getScopeId()));
//            if (index == null) {
//                continue;
//            }
//            TimeInventoryReleaseParam param = params.get(index);
//
//            List<TimeInventoryItemModel> items = PositionsConverter.decodeLockPositions(lock.getDate(), lock.getPositions(), lock.getUnit());
//            List<TimeInventoryItemModel> unReleasedItems = new ArrayList<>();
//            // 区别每条锁记录的整体释放 | 部分释放
//            if (CollectionUtils.isEmpty(param.getItems())) {
//                // 重置 ReleaseParam 的 items
//                param.setItems(items);
//            } else {
//                // 对于部分释放的，需要重新记录未释放的点
//                Collections.sort(param.getItems(), new Comparator<TimeInventoryItemModel>() {
//                    @Override
//                    public int compare(TimeInventoryItemModel o1, TimeInventoryItemModel o2) {
//                        return o1.getDate().compareTo(o2.getDate());
//                    }
//                });
//
//                int indexOfLockItems = 0;
//                int indexOfParamItems = 0;
//                for (TimeInventoryItemModel item : param.getItems()) {
//                    while (indexOfLockItems < items.size() && items.get(indexOfLockItems).getDate().compareTo(item.getDate()) != 0) {
//                        unReleasedItems.add(items.get(indexOfLockItems));
//                        indexOfLockItems++;
//                    }
//                    // FIXME: 临时逻辑 @fangfeng @2018-05-23
//                    if (indexOfLockItems >= items.size()) {
//                        break;
//                    }
//                    param.getItems().get(indexOfParamItems).setState(items.get(indexOfLockItems).getState());
//
//                    indexOfLockItems++;
//                    indexOfParamItems++;
//                }
//                if (indexOfLockItems < items.size()) {
//                    unReleasedItems.addAll(items.subList(indexOfLockItems, items.size()));
//                }
//                if (indexOfParamItems != param.getItems().size()) {
//                    LOGGER.error("release time inventory failed - lock Record does not match release Items. lockRecord: {}, release param: {}", lock, param);
//                    throw ErrorCode.buildException(ErrorCode.OP_FAILED, "release Failed.");
//                }
//            }
//
//            setTimeInventoryStates(param, getAllowedStates(TimeInventoryModel.STATE_USED));
//            // 更新时间库存锁记录
//            try {
//                timeInventoryLockMapper.releaseTimeInventoryLock(lock);
//                if (!CollectionUtils.isEmpty(unReleasedItems)) {
//                    String positions = PositionsConverter.encodeLockPositions(lock.getDate(), unReleasedItems, lock.getUnit());
//                    lock.setPositions(positions);
//                    timeInventoryLockMapper.createTimeInventoryLock(lock);
//                }
//            } catch (RuntimeException e) {
//                LOGGER.error("release time inventory failed - update lock record failed : {}", param.toString());
//                throw ErrorCode.buildException(ErrorCode.OP_FAILED, "update Lock Record Failed");
//            }
//        }
//    }
//
//    /**
//     * 通用的更新时间库存单元对象的方法
//     * @param param
//     * @param allowed 允许存在的更新前的状态集合
//     */
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class})
//    protected void setTimeInventoryStates(BaseTimeInventoryStateOpParam param, Set<Integer> allowed) {
//
//        Assert.notNull(param, String.valueOf(this.getClass()));
//        Assert.notNull(param.getScopeId(), "BaseTimeInventoryStateOpParam.scopeId");
//        Assert.notNull(param.getItems(), "BaseTimeInventoryStateOpParam.items");
//        Assert.notNull(param.getDates(), "BaseTimeInventoryStateOpParam.dates");
//
//        // 针对不操作时间库存的特殊情况(例如现在医美不锁库存)
//        if (param.getItems().size() == 0) {
//            return;
//        }
//
//        // FIXME: 目前暂定 retry 三次，后续根据改数据库的冲突情况具体修改，估计一两年没问题 @fangfeng @2018-03-30
//        int retryLimit = 3;
//        for (int retry = 1;retry <= retryLimit;retry++) {
//            // 针对操作时间库存的正常情况
//            List<TimeInventoryModel> inventories = timeInventoryMapper.getTimeInventoriesBySeparateDates(
//                    param.getScope(), param.getScopeId(), param.getDates());
//
//            if (CollectionUtils.isEmpty(inventories)) {
//                LOGGER.warn("time inventory not exist : {}", param.toString());
//                throw ErrorCode.buildException(ErrorCode.INVENTORY_NOT_EXIST);
//            }
//
//            List<InternalTimeInventoryModel> internals = new ArrayList<>();
//            Map<Date, InternalTimeInventoryModel> mappings = new HashMap<>();
//            for (TimeInventoryModel inventory : inventories) {
//                InternalTimeInventoryModel internal = InternalTimeInventoryModel.parse(inventory);
//                internals.add(internal);
//                mappings.put(internal.getDate(), internal);
//            }
//
//            // FIXME: 时间库存一期兼容方案，订单时刻不能是 [关闭] (但是订单占用的其它时间可以为 [关闭]) @fangfeng @2018-10-17
//            if (param instanceof TimeInventoryLockParam) {
//                TimeInventoryItemModel item = ((TimeInventoryLockParam) param).getItem();
//                InternalTimeInventoryModel internal = mappings.get(DateUtil.toDate(item.getDate()));
//                if (internal.getState(item.getPosition()) == TimeInventoryModel.STATE_DISABLED) {
//                    LOGGER.error("lock moment is STATE_DISABLED : {}", ((TimeInventoryLockParam) param).toString());
//                    throw ErrorCode.buildException(ErrorCode.OP_FAILED, "lock moment is STATE_DISABLED");
//                }
//            }
//
//            List<TimeInventoryItemModel> modifiedItems = new ArrayList<>();
//            for (TimeInventoryItemModel item : param.getItems()) {
//                Assert.notNull(item.getDate(), "TimeInventoryItemModel.date");
//                InternalTimeInventoryModel internal = mappings.get(DateUtil.toDate(item.getDate()));
//                if (internal == null) {
//                    LOGGER.warn("time inventory not exist : {}", param.toString());
//                    throw ErrorCode.buildException(ErrorCode.INVENTORY_NOT_EXIST);
//                }
//                // Assert time unit is equal;
//                Assert.equals(internal.getUnit(), param.getUnit(), "TIME UNIT");
//
//                // src state = dest state
//                if (internal.getState(item.getPosition()) == item.getState()) {
//                    // src state = STATE_USED  dest state = STATE_USED is not allowed
//                    if (internal.getState(item.getPosition()) == TimeInventoryModel.STATE_USED) {
//                        LOGGER.error("multi attempt to lock same time inventory : {}", param.toString());
//                        throw ErrorCode.buildException(ErrorCode.OP_FAILED, "multi attempt to lock same time inventory");
//                    }
//                    continue;
//                }
//
//                if (!allowed.contains(internal.getState(item.getPosition()))) {
//                    LOGGER.error("time inventory state from {} to {} is not allowed, param: {}", internal.getState(item.getPosition()), item.getState(), param.toString());
//                    throw ErrorCode.buildException(ErrorCode.OP_FAILED, String.format("time inventory state from %d to %d is not allowed", internal.getState(item.getPosition()), item.getState()));
//                }
//
//                modifiedItems.add(item);
//
//                // FIXME: 临时支持 【已约】->【未约】 or 【已约】->【关闭】@fangfeng @2018-05-23
//                // internal.setState(item.getPosition(), param.state());
//                internal.setState(item.getPosition(), item.getState());
//            }
//
//            try {
//                batchUpdateTimeInventories(internals);
//            } catch (Exception e) {
//                LOGGER.error("batch update time inventories failed : {}, the {} try", param.toString(), retry);
//                if (retry+1 > retryLimit) {
//                    LOGGER.error("retried more than {} times", retryLimit);
//                    throw ErrorCode.buildException(ErrorCode.OP_FAILED, "please retry");
//                }
//                continue;
//            }
//
//
//
//            Date baseDate = param.getDates().get(0);
//            TimeInventoryOpModel inventoryOp = new TimeInventoryOpModel();
//            inventoryOp.setScope(param.getScope());
//            inventoryOp.setScopeId(param.getScopeId());
//            inventoryOp.setPositions(PositionsConverter.encodeOpPositions(baseDate, modifiedItems, param.getUnit()));
//            inventoryOp.setUnit(param.getUnit());
//            inventoryOp.setDate(baseDate);
//            inventoryOp.setState(param.state());
//            inventoryOp.setOpSource(param.getOpSource());
//            try {
//                timeInventoryOpMapper.insertTimeInventoryOp(inventoryOp);
//            } catch (Exception e) {
//                LOGGER.error("insert time inventory ops log failed : {}", inventoryOp.toString());
//                throw ErrorCode.buildException(ErrorCode.OP_FAILED, "insert inventory ops log failed");
//            }
//            return;
//        }
//    }
//
//    private Set<Integer> getAllowedStates(int... allowed) {
//        Set<Integer> ret = new HashSet<>();
//        for (int state : allowed) {
//            ret.add(state);
//        }
//        return ret;
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
//    protected boolean batchUpdateTimeInventories(List<InternalTimeInventoryModel> internals) {
//        for (InternalTimeInventoryModel internal : internals) {
//            if (internal.getChangedValues().size() != 0) {
//                int count = timeInventoryMapper.updateTimeInventory(internal);
//                if (count != 1) {
//                    throw ErrorCode.buildException(ErrorCode.OP_FAILED, "update rows failed!");
//                }
//            }
//        }
//        return true;
//    }
//
//    public Map<Integer, TimeInventoryCreatePolicy> getCreatePolicies() {
//        return createPolicies;
//    }
//
//    public void setCreatePolicies(Map<Integer, TimeInventoryCreatePolicy> createPolicies) {
//        this.createPolicies = createPolicies;
//    }
//
//    /** 历史原因 T_T */
//    public boolean addCreatePolicy(Integer key, TimeInventoryCreatePolicy createPolicy) {
//        createPolicies.put(key, createPolicy);
//        return true;
//    }
//
//    public Map<Integer, TimeInventoryLockPolicy> getLockPolicies() {
//        return lockPolicies;
//    }
//
//    public void setLockPolicies(Map<Integer, TimeInventoryLockPolicy> lockPolicies) {
//        this.lockPolicies = lockPolicies;
//    }
//
//    /** 历史原因 T_T */
//    public boolean addLockPolicy(Integer key, TimeInventoryLockPolicy lockPolicy) {
//        lockPolicies.put(key, lockPolicy);
//        return true;
//    }

    /**
     * 静态嵌套类，仅应用于 {@code this.releaseTimeInventories()}
     * @return
     */
    protected static class ScopeElement {

        public Integer scope;
        public String scopeId;

        ScopeElement(Integer scope, String scopeId) {
            this.scope = scope;
            this.scopeId = scopeId;
        }
    }
}
