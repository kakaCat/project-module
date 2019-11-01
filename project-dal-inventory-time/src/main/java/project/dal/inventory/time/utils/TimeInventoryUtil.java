package project.dal.inventory.time.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.entity.TimeInventoryViewModel;
import project.dal.inventory.time.policy.TimeInventoryLockPolicy;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class TimeInventoryUtil {

    public static int OVERDUE_STATE =6;

    public static int CLOSS =1;

    public static void overdue(List<TimeInventoryViewModel> temporaryViews){
        Date currentTime = new Date();
        // 处理当前时间导致的时间过期
        for (int i = 0; i < temporaryViews.size(); i++) {
            TimeInventoryViewModel view = temporaryViews.get(i);
            for (TimeInventoryItemModel item : view.getItems()) {
                if (item.getDate().compareTo(currentTime) <= 0) {
                    item.setState(OVERDUE_STATE);
                    item.setStateMessage(getStateMessage(OVERDUE_STATE));
                }
            }
        }

    }

    private static String getStateMessage(int state) {
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
            case 6:
                return "过期";
            default:
                return "";
        }
    }


    /**
     * 验证时间库存对象列表中所有的时间库存对象的时间粒度是否一致
     * @param inventories 时间库存对象列表
     */
    public static void checkUnits(List<TimeInventoryModel> inventories) {
        if (CollectionUtils.isEmpty(inventories)) {
            return;
        }
        int stdUnit = inventories.get(0).getTimeUnit();
        for (TimeInventoryModel inventory : inventories) {
            if (stdUnit != inventory.getTimeUnit()) {
                log.warn("time unit not equal : expected={}, actual={} (inventories : {})", stdUnit, inventory.getTimeUnit(), inventories);

            }
        }
    }

    /**
     * 将 {@code int[]} 组织成 {@code Set<Integer>} 集合
     * @param allowed
     * @return
     */
    public static Set<Integer> getAllowedStates(int... allowed) {
        Set<Integer> ret = new HashSet<>();
        for (int state : allowed) {
            ret.add(state);
        }
        return ret;
    }

    public static void preProcess(List<TimeInventoryViewModel> temporaryViews, TimeInventoryLockPolicy.TimeInventoryLockValues values,Set<Integer> allowed,int positionLength) {
        int preAvailableUnits = 0;
        TimeInventoryModel pro = null;
        TimeInventoryModel current = null;
        for (int i=0;i<temporaryViews.size();i++) {
            TimeInventoryViewModel view = temporaryViews.get(i);
            current = view.getInventory();
            // 时间库存对象不连续的情况，重置离前一个最近不可约库存单元的单元差
            if (i!=0 && DateUtil.getIntervalDay(pro.getInventoryTime(), current.getInventoryTime()) != 1) {
                preAvailableUnits = 0;
            }
            for (int j=0;j<positionLength;j++) {
                int state = view.getItems().get(j).getState();
                if (allowed.contains(state)) {
                    if (preAvailableUnits < values.previous) {
                        state = CLOSS;
                        view.getItems().get(j).setState(state);
                        view.getItems().get(j).setStateMessage(getStateMessage(state));
                    }
                    preAvailableUnits++;
                } else {
                    preAvailableUnits = 0;
                }
            }
            pro = current;
        }

    }

    public static void suffixProcess(List<TimeInventoryViewModel> temporaryViews, TimeInventoryLockPolicy.TimeInventoryLockValues values,Set<Integer> allowed, int positionLength) {
        int followAvailableUnit = 0;
        TimeInventoryModel pro = null;
        TimeInventoryModel current = null;
        for (int i=temporaryViews.size()-1;i>=0;i--) {
            TimeInventoryViewModel view = temporaryViews.get(i);
            current = view.getInventory();
            if (i!=temporaryViews.size()-1 && DateUtil.getIntervalDay(pro.getInventoryTime(), current.getInventoryTime()) != 1) {
                followAvailableUnit = 0;
            }
            for (int j=positionLength-1;j>=0;j--) {
                int state = view.getItems().get(j).getState();
                if (allowed.contains(state)) {
                    followAvailableUnit++;
                    if (followAvailableUnit < values.following) {
                        state = CLOSS;
                        view.getItems().get(j).setState(state);
                        view.getItems().get(j).setStateMessage(getStateMessage(state));
                    }
                } else {
                    followAvailableUnit = 0;
                }
            }
            pro = current;
        }

    }
}
