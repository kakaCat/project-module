//package project.dal.inventory.time.utils;
//
//import com.helijia.pivot.inventory.model.TimeInventoryModel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author fangfeng
// * @since 2018/5/31
// */
//public class TimeInventoryUtil {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TimeInventoryUtil.class);
//
//    /**
//     * 验证时间库存对象列表中所有的时间库存对象的时间粒度是否一致
//     * @param inventories 时间库存对象列表
//     */
//    public static void checkUnits(List<TimeInventoryModel> inventories) {
//        if (CollectionUtils.isEmpty(inventories)) {
//            return;
//        }
//        int stdUnit = inventories.get(0).getUnit();
//        for (TimeInventoryModel inventory : inventories) {
//            if (stdUnit != inventory.getUnit()) {
//                LOGGER.warn("time unit not equal : expected={}, actual={} (inventories : {})", stdUnit, inventory.getUnit(), inventories);
//                throw ErrorCode.buildException(ErrorCode.NOT_EQUAL, "TIME UNIT");
//            }
//        }
//    }
//
//    /**
//     * 将 {@code int[]} 组织成 {@code Set<Integer>} 集合
//     * @param allowed
//     * @return
//     */
//    public static Set<Integer> getAllowedStates(int... allowed) {
//        Set<Integer> ret = new HashSet<>();
//        for (int state : allowed) {
//            ret.add(state);
//        }
//        return ret;
//    }
//}
