//package project.dal.inventory.time.policy.impl;
//
//import com.helijia.pivot.inventory.model.InternalTimeInventoryModel;
//import com.helijia.pivot.inventory.model.TimeInventoryModel;
//import com.helijia.pivot.inventory.param.TimeInventoryCreateParam;
//import com.helijia.pivot.inventory.spi.TimeInventoryCreatePolicy;
//
///**
// * 临时白班策略(Day Shift)
// * 【未约】Unused 包括 [10:00, 21:30) 内的所有时刻
// * 【关闭】Disable 包括 [00:00, 10:00) & [21:30, 24:00) 的所有时刻
// * 对应不同的时间粒度，保证时间展示相同
// * <b>不兼容:</b>
// * <p>
// *     对于时间粒度不是 30 约数的，可能出现不兼容现象
// * </p>
// *
// * <p>
// * 用于过渡时期，仅为了适应新时间库存一期上线，且达到使手艺人无感知的目的
// * </p>
// * @author fangfeng
// * @since 2018/5/23
// */
//public class TemporaryDayShift implements TimeInventoryCreatePolicy {
//
//    /**
//     * 前闭后开 [10:00, 21:30)
//     * 【未约】起始时间
//     */
//    private static final int START_TIME_FOR_UNUSED = 600;   // 单位分钟 [10时]
//    private static final int END_TIME_FOR_UNUSED = 1290;    // 单位分钟 [21时30分]
//    private static final int WHOLE_DAY_IN_MINUTES = 1440;   // 一天 1440 分钟
//    private static final int STATE_BITS = 2;
//    private static final int POSITIONS_PRE_INTEGER = Integer.SIZE / STATE_BITS;
//
//    @Override
//    public int name() {
//        return 3;
//    }
//
//    @Override
//    public TimeInventoryCreateValues create(TimeInventoryCreateParam param) {
//
//        TimeInventoryCreateValues values = new TimeInventoryCreateValues();
//
//        int unitMinutes = InternalTimeInventoryModel.getUnitMinutes(param.getUnit());
//        int currentMinutes = 0;
//
//        for (int i=0;currentMinutes < WHOLE_DAY_IN_MINUTES;i++) {
//            if (START_TIME_FOR_UNUSED <= currentMinutes && currentMinutes < END_TIME_FOR_UNUSED) {
//                setValues(values, i, TimeInventoryModel.STATE_UNUSED);
//            } else {
//                setValues(values, i, TimeInventoryModel.STATE_DISABLED);
//            }
//            currentMinutes += unitMinutes;
//        }
//        return values;
//    }
//
//    private void setValues(TimeInventoryCreateValues values, int position, int state) {
//        switch (position / POSITIONS_PRE_INTEGER) {
//            case 0:
//                values.value0 |= (state << leftSwitchOfBits(position));
//                break;
//            case 1:
//                values.value1 |= (state << leftSwitchOfBits(position));
//                break;
//            case 2:
//                values.value2 |= (state << leftSwitchOfBits(position));
//                break;
//            case 3:
//                values.value3 |= (state << leftSwitchOfBits(position));
//                break;
//            case 4:
//                values.value4 |= (state << leftSwitchOfBits(position));
//                break;
//            case 5:
//                values.value5 |= (state << leftSwitchOfBits(position));
//            default:
//                return;
//        }
//    }
//
//    private int leftSwitchOfBits(int position) {
//        return (position % POSITIONS_PRE_INTEGER) * STATE_BITS;
//    }
//
//}
