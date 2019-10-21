package project.dal.inventory.time.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dal.inventory.time.api.TimeInventoryViewConverter;
import project.dal.inventory.time.convert.TimeConvert;
import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.entity.TimeInventoryLockModel;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.entity.TimeInventoryViewModel;
import project.dal.inventory.time.metadata.ByteState;
import project.dal.inventory.time.metadata.LockState;
import project.dal.inventory.time.metadata.TimeUnitState;
import project.dal.inventory.time.param.DalParam;
import project.dal.inventory.time.param.TimeInventoryListParam;
import project.dal.inventory.time.param.TimeInventoryLockParam;
import project.dal.inventory.time.policy.Policyfactory;
import project.dal.inventory.time.policy.TimeInventoryLockPolicy;
import project.dal.inventory.time.realm.TimeInventoryRealm;
import project.dal.inventory.time.repository.db.TimeInventoryLockMapper;
import project.dal.inventory.time.repository.db.TimeInventoryMapper;
import project.dal.inventory.time.utils.DateUtil;
import project.dal.inventory.time.utils.PositionsConverter;
import project.dal.inventory.time.utils.TimeInventoryUtil;

import java.util.*;


@Service
public class DefaultTimeInventoryViewConverter implements TimeInventoryViewConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTimeInventoryViewConverter.class);
//    private ResourceBundle resource = ResourceBundle.getBundle("pivot/inventory/message/inventory");

//    @Autowired
//    DefaultTimeInventoryService timeInventoryService;
    @Autowired
    private TimeInventoryMapper inventoryMapper;
    @Autowired
    private TimeInventoryLockMapper inventoryLockMapper;

    @Autowired
    private Policyfactory policyfactory;

    @Override
    public TimeInventoryViewModel convert(TimeInventoryModel inventory) {
        TimeInventoryRealm realm = new TimeInventoryRealm(inventory);
        TimeInventoryViewModel view = new TimeInventoryViewModel();
        view.setInventory(inventory);
        view.setUnitMessage(getUnitMessage(inventory.getTimeUnit()));
        view.setItems(realm.addItems());


        return view;
    }

    @Override
    public List<TimeInventoryViewModel> convert(List<TimeInventoryModel> inventories) {
//        commonValidate(inventories);
        List<TimeInventoryViewModel> views = new ArrayList<>(inventories.size());
        for (TimeInventoryModel inventory : inventories) {
            views.add(convert(inventory));
        }

        // 额外加工 VIEW STATE STATE_USED_MILESTONE 【已约-里程碑】
        internalConvertWithStateUsedMilestone(views);
        return views;
    }

    @Override
    public List<TimeInventoryViewModel> convert(List<TimeInventoryModel> inventories, DalParam dalParam) {
        TimeInventoryLockParam lockParam = (TimeInventoryLockParam) dalParam;
        return internalConvert(
                inventories,
                lockParam,
                TimeInventoryUtil.getAllowedStates(ByteState.STATE_UNUSED.getState()),
                TimeInventoryViewModel.STATE_DISABLE);
    }

    @Override
    public List<TimeInventoryViewModel> convert(List<TimeInventoryModel> inventories,  DalParam dalParam, boolean flag) {
        TimeInventoryLockParam lockParam = (TimeInventoryLockParam) dalParam;
        return internalConvert(
                inventories,
                lockParam,
                TimeInventoryUtil.getAllowedStates(ByteState.STATE_DISABLED.getState(), ByteState.STATE_UNUSED.getState()),
                flag ? TimeInventoryViewModel.STATE_AFFECTED : TimeInventoryViewModel.STATE_DISABLE);
    }

    /**
     * 内部统一转换的方法
     * @param inventories 时间库存对象列表
     * @param lockParam 锁参数
     * @param allowed 允许转换到 {@code TimeInventoryModel.STATE_USED} 的状态集
     * @param affectedState 受影响状态的实际展示结果
     * @return 加工后的时间库存视图对象列表
     */
    private List<TimeInventoryViewModel> internalConvert(List<TimeInventoryModel> inventories, TimeInventoryLockParam lockParam, Set<Integer> allowed, Integer affectedState) {
//        commonValidate(inventories);

        // 获取 LOCK 策略
        TimeInventoryLockPolicy policy = policyfactory.getLockPolicies(lockParam.getPolicy());
//        TimeInventoryLockPolicy policy = timeInventoryService.getLockPolicies().get(lockParam.getPolicy());
//        if (policy == null) {
//            LOGGER.warn("cannot find lock policy - required policyId : {}", lockParam.getPolicy());
//            throw ErrorCode.buildException(ErrorCode.CANNOT_FIND_POLICY, "LockPolicy", lockParam.getPolicy());
//        }
        // values.previous 前置锁库存单元数 values.following 后置锁库存单元
        TimeInventoryLockPolicy.TimeInventoryLockValues values = policy.lock(lockParam);

        List<TimeInventoryModel> temporaryInventories = inventories;
        if (lockParam.isConvertWithRelatedInventories()) {
            // 临时时间库存对象列表(除查询的外，额外包含前置若干天的记录)
            temporaryInventories = getTemporaryInventories(inventories, values);
        }

        // temporary inventories -> views
        List<TimeInventoryViewModel> temporaryViews = new ArrayList<>(temporaryInventories.size());
        for (TimeInventoryModel inventory : temporaryInventories) {
            // 对每天时间库存的每个item作转换
            temporaryViews.add(convert(inventory));
        }

        // 忽略特定锁对象锁定的时间库存(用于支持改约时间在原预约时间附近)
        if (lockParam.isIgnoreCurLockScope()) {
            ignoreCurrentLockScope(temporaryViews, lockParam);
        }

        // 当前库存单元 到 前一个最近不可约库存单元 的单元差
        int preAvailableUnits = 0;
        // 当前库存单元 到 后一个最近不可约库存单元 的单元差
        int followAvailableUnit = 0;
        int positionLength = TimeUnitState.getTimeUnitState(inventories.get(0).getTimeUnit()).getLength();

        if (lockParam.isConvertWithCurrentTime()) {
            // 当前时间点
            Date currentTime = new Date();
            // 处理当前时间导致的时间过期
            for (int i = 0; i < temporaryViews.size(); i++) {
                TimeInventoryViewModel view = temporaryViews.get(i);
                for (TimeInventoryItemModel item : view.getItems()) {
                    if (item.getDate().compareTo(currentTime) <= 0) {
                        item.setState(affectedState);
                        item.setStateMessage(getStateMessage(affectedState));
                    }
                }
            }
        }

        // 处理前置时间导致的不可约
        for (int i=0;i<temporaryViews.size();i++) {
            TimeInventoryViewModel view = temporaryViews.get(i);
            // 时间库存对象不连续的情况，重置离前一个最近不可约库存单元的单元差
            if (i!=0 && DateUtil.getIntervalDay(temporaryInventories.get(i-1).getInventoryTime(), temporaryInventories.get(i).getInventoryTime()) != 1) {
                preAvailableUnits = 0;
            }
            for (int j=0;j<positionLength;j++) {
                int state = view.getItems().get(j).getState();
                if (allowed.contains(state)) {
                    if (preAvailableUnits < values.previous) {
                        state = affectedState;
                        view.getItems().get(j).setState(state);
                        view.getItems().get(j).setStateMessage(getStateMessage(state));
                    }
                    preAvailableUnits++;
                } else {
                    preAvailableUnits = 0;
                }
            }
        }

        // 处理后置时间导致的不可约
        for (int i=temporaryViews.size()-1;i>=0;i--) {
            TimeInventoryViewModel view = temporaryViews.get(i);
            if (i!=temporaryViews.size()-1 && DateUtil.getIntervalDay(temporaryInventories.get(i).getInventoryTime(), temporaryInventories.get(i+1).getInventoryTime()) != 1) {
                followAvailableUnit = 0;
            }
            for (int j=positionLength-1;j>=0;j--) {
                int state = view.getItems().get(j).getState();
                if (allowed.contains(state)) {
                    followAvailableUnit++;
                    if (followAvailableUnit < values.following) {
                        state = affectedState;
                        view.getItems().get(j).setState(state);
                        view.getItems().get(j).setStateMessage(getStateMessage(state));
                    }
                } else {
                    followAvailableUnit = 0;
                }
            }
        }

        // 移除加工过程中产生的多余的时间库存对象
        int previousDays = 0;
        while (!inventories.get(0).getInventoryTime().equals(temporaryViews.get(previousDays).getInventory().getInventoryTime())) {
            previousDays++;
        }
        List<TimeInventoryViewModel> views = temporaryViews.subList(previousDays, previousDays + inventories.size());

        if (lockParam.isConvertUMS()) {
            // 额外加工 VIEW STATE STATE_USED_MILESTONE 【已约-里程碑】
            internalConvertWithStateUsedMilestone(views);
        }

//        reconvertForDisableWholeDay(views);

        return views;
    }

    /**
     * convert 与 全天休息 冲突时，优先全天休息的逻辑，不做 convert 处理
     * @param views
     */
//    private void reconvertForDisableWholeDay(List<TimeInventoryViewModel> views) {
//        for (TimeInventoryViewModel view : views) {
//            TimeInventoryModel inventory = view.getInventory();
//            TimeInventoryViewModel realView = convert(inventory);
//            boolean isDisableWholeDay = true;
//            for (TimeInventoryItemModel item : realView.getItems()) {
//                if (item.getState() != TimeInventoryModel.STATE_DISABLED) {
//                    isDisableWholeDay = false;
//                    break;
//                }
//            }
//            if (isDisableWholeDay == false) {
//                continue;
//            }
//            for (TimeInventoryItemModel item : view.getItems()) {
//                item.setState(TimeInventoryModel.STATE_DISABLED);
//                item.setStateMessage(getStateMessage(TimeInventoryModel.STATE_DISABLED));
//            }
//        }
//    }

    /**
     * 忽略特定锁对象锁定的时间库存(用于支持改约时间在原预约时间附近)
     * 目前邻里店订单/预约单存在一个 lockScopeId 锁定多个不同 scopeId 的情况。
     * 但能够保证在限定 (scope, scopeId, lockScope, lockScopeId) 后，有效记录最多只有一条
     * @param views 时间库存视图对象列表
     * @param lockParam 锁对象
     */
    private void ignoreCurrentLockScope(List<TimeInventoryViewModel> views, TimeInventoryLockParam lockParam) {

        List<TimeInventoryLockModel> inventoryLocks = inventoryLockMapper.getTimeInventoryLocksByLockScope(lockParam.getLockScope(), lockParam.getLockScopeId(), LockState.STATE_ON.getState());

        TimeInventoryLockModel inventoryLock = null;
        for (TimeInventoryLockModel lock : inventoryLocks) {
            if (lock.getScope() != lockParam.getScope() || !lockParam.getScopeId().equals(lock.getScopeId())) {
                continue;
            }
            if (inventoryLock != null) {
                LOGGER.warn("multi STATE_ON records with the same lockScope. lockParam : {}, inventoryLocks : {}", lockParam, inventoryLocks);

            }
            inventoryLock = lock;
        }

        if (inventoryLock == null) {
            /** 邻里店订单/预约单初次锁时间 和 改约均会尝试锁定 */
            return;
        }

        Map<Date, TimeInventoryViewModel> map = new HashMap<>();
        for (TimeInventoryViewModel view : views) {
            map.put(view.getInventory().getInventoryTime(), view);
        }

        List<TimeInventoryItemModel> items = PositionsConverter.decodeLockPositions(inventoryLock.getInventoryTime(), inventoryLock.getPositions(), inventoryLock.getTimeUnit());
        for (TimeInventoryItemModel item : items) {
            TimeInventoryViewModel view = map.get(DateUtil.toDate(item.getDate()));
            if (view == null) {
                continue;
            }
            if (view.getItems().get(item.getPosition()).getState() == TimeInventoryViewModel.STATE_USED) {
                view.getItems().get(item.getPosition()).setState(item.getState());
                view.getItems().get(item.getPosition()).setStateMessage(getStateMessage(item.getState()));
            }
        }
    }

    /**
     * 获取临时时间库存对象列表，主要包括额外得到前置、后置时间库存对象数据
     * @param inventories 时间库存对象列表
     * @param lockValues
     * @return
     */
    private List<TimeInventoryModel> getTemporaryInventories(List<TimeInventoryModel> inventories, TimeInventoryLockPolicy.TimeInventoryLockValues lockValues) {
        int positionLength = TimeUnitState.getTimeUnitState(inventories.get(0).getTimeUnit()).getLength();
        List<TimeInventoryModel> temporaryInventories = new ArrayList<>();

        // previous 从时间库存占用UNIT数量 -> 占用天数(不足的按完整一天计算)
        int previous = (lockValues.previous + positionLength - 1) / positionLength;

        // 往前推若干天，但不能小于 当日日期
        Date preDate = DateUtil.preDate(inventories.get(0).getInventoryTime(), previous);
        if (preDate.before(DateUtil.toDate(new Date()))) {
            preDate = DateUtil.toDate(new Date());
        }
        if (preDate.before(inventories.get(0).getInventoryTime())) {
            // 查询前若干天的时间库存
            TimeInventoryListParam listParam = new TimeInventoryListParam();
            listParam.setStartDate(preDate);
            listParam.setEndDate(inventories.get(0).getInventoryTime());
            listParam.setScope(inventories.get(0).getScope());
            listParam.setScopeId(inventories.get(0).getScopeId());
            listParam.setTimeUnit(inventories.get(0).getTimeUnit());
//            List<TimeInventoryModel> preInventories = inventoryMapper.getTimeInventoriesByDates(listParam);
//            temporaryInventories.addAll(preInventories);
        }

        // 真正需要查询的若干天时间库存
        temporaryInventories.addAll(inventories);

        // following 从时间库存占用UNIT数字 -> 占用天数(不足的按完整一天计算)
        int following = ((lockValues.following-1) + positionLength - 1) / positionLength;
        // 后若干天的时间库存
        TimeInventoryListParam listParam = new TimeInventoryListParam();
        Date latestDate = inventories.get(inventories.size()-1).getInventoryTime();
        listParam.setStartDate(DateUtil.nextDate(latestDate, 1));
        listParam.setEndDate(DateUtil.nextDate(listParam.getStartDate(), following));
        listParam.setScope(inventories.get(0).getScope());
        listParam.setScopeId(inventories.get(0).getScopeId());
        listParam.setTimeUnit(inventories.get(0).getTimeUnit());
//        List<TimeInventoryModel> nextInventories = inventoryMapper.getTimeInventoriesByDates(listParam);
//        temporaryInventories.addAll(nextInventories);

        TimeInventoryUtil.checkUnits(temporaryInventories);
        return temporaryInventories;
    }

//    private InternalTimeInventoryModel getInternalTimeInventoryModel(TimeInventoryModel inventory) {
//        if (inventory instanceof InternalTimeInventoryModel) {
//            return (InternalTimeInventoryModel) inventory;
//        }
//        return InternalTimeInventoryModel.parse(inventory);
//    }

    /**
     * 内部转换方法，用于加工 STATE_USED_MILESTONE 【已约-里程碑】
     * @param
     * @return
     */
    private void internalConvertWithStateUsedMilestone(List<TimeInventoryViewModel> views) {
        TimeInventoryModel stdInventory = views.get(0).getInventory();
        int scope = stdInventory.getScope();
        String scopeId = stdInventory.getScopeId();
        int unit = stdInventory.getTimeUnit();
        Date startDate = stdInventory.getInventoryTime();
        // [startDate, endDate) 前闭后开，因此在需要查询的最后一天再加一天
        Date endDate = DateUtil.nextDate(views.get(views.size()-1).getInventory().getInventoryTime(), 1);
        int unitMinutes = stdInventory.getTimeUnitState().getUnitValue();
        List<TimeInventoryLockModel> inventoryLocks = inventoryLockMapper.getTimeInventoryLocksByScopeAndDates(scope, scopeId, LockState.STATE_ON.getState(), startDate, endDate);
        Map<Date, TimeInventoryViewModel> map = new HashMap<>();
        for (TimeInventoryViewModel view : views) {
            map.put(view.getInventory().getInventoryTime(), view);
        }

        for (TimeInventoryLockModel inventoryLock : inventoryLocks) {
            TimeInventoryViewModel view = map.get(DateUtil.toDate(inventoryLock.getInventoryTime()));
            int minutes = DateUtil.getIntervalMinutes(view.getInventory().getInventoryTime(), inventoryLock.getInventoryTime());
            int position = minutes / unitMinutes;
            if (view.getItems().get(position).getState() == TimeInventoryViewModel.STATE_USED) {
                view.getItems().get(position).setState(TimeInventoryViewModel.STATE_USED_MILESTONE);
                view.getItems().get(position).setStateMessage(getStateMessage(TimeInventoryViewModel.STATE_USED_MILESTONE));
            }
        }
    }


    private String getStateMessage(int state) {
        switch (state) {
            case 0:
                return "无效";
            case 1:
                return "关闭";
            case 2:
                return "未约";
            case 3:
                return "已约";
            case 4:
                return "已约-里程碑";
            case 5:
                return "被影响";
            default:
                return "";
        }
    }

    private String getUnitMessage(int unit) {
        switch (unit) {

            case 1:
                return "15分钟 ";
            case 2:
                return "30分钟";
            case 3:
                return "60分钟";

            default:
                return "";
        }
    }

//    public DefaultTimeInventoryService getTimeInventoryService() {
//        return timeInventoryService;
//    }

//    public void setTimeInventoryService(DefaultTimeInventoryService timeInventoryService) {
//        this.timeInventoryService = timeInventoryService;
//    }
}
