//package project.dal.inventory.time.param;
//
//import com.helijia.pivot.inventory.model.TimeInventoryModel;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author fangfeng
// * @since 2018/6/21
// */
//public class TimeInventoryLegalizeParam extends BaseTimeInventoryStateOpParam {
//
//    private static final Set<Integer> TARGET_STATE_SET = new HashSet(
//            Arrays.asList(new Integer[]{TimeInventoryModel.STATE_DISABLED, TimeInventoryModel.STATE_UNUSED})
//    );
//
//    /**
//     * LegalizeParam 允许的状态转换包括:
//     * {@link TimeInventoryModel#STATE_INVALID} -> {@link TimeInventoryModel#STATE_DISABLED}
//     * &
//     * {@link TimeInventoryModel#STATE_INVALID} -> {@link TimeInventoryModel#STATE_UNUSED}
//     *
//     * 由于父类接口的限制，此处实现只包括一个可变状态转换
//     * @return {@link TimeInventoryModel#STATE_DISABLED}
//     */
//    @Override
//    public int state() {
//        return TimeInventoryModel.STATE_DISABLED;
//    }
//
//    public static Set<Integer> getTargetStateSet() {
//        return TARGET_STATE_SET;
//    }
//}
