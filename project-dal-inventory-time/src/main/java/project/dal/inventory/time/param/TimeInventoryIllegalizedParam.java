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
//public class TimeInventoryIllegalizedParam extends BaseTimeInventoryStateOpParam {
//
//    private static final Set<Integer> TARGET_STATE_SET = new HashSet<>(
//        Arrays.asList(new Integer[]{TimeInventoryModel.STATE_INVALID})
//    );
//
//    @Override
//    public int state() {
//        return TimeInventoryModel.STATE_INVALID;
//    }
//
//    public static Set<Integer> getTargetStateSet() {
//        return TARGET_STATE_SET;
//    }
//}
