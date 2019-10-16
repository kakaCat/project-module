package project.dal.inventory.time.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import project.dal.inventory.time.entity.TimeInventoryModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class TimeInventoryUtil {


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
}
