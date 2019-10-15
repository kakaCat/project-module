package project.dal.inventory.time.policy.impl;


import project.dal.inventory.time.convert.TimeConvert;
import project.dal.inventory.time.metadata.ByteState;
import project.dal.inventory.time.metadata.TimeUnitState;
import project.dal.inventory.time.param.DalParam;
import project.dal.inventory.time.param.TimeInventoryCreateParam;
import project.dal.inventory.time.policy.TimeInventoryCreatePolicy;

/**
 * 默认白班(Day Shift)
 * 【未约】Unused 包括 [09:00, 23:00) 内的所有时刻
 * 【关闭】Disable 包括 [00:00, 09:00) & [23:00, 24:00) 的所有时刻
 * 对应不同的时间粒度，保证时间展示相同
 * <b>不兼容:</b>
 * <p>
 *     对于时间粒度不是 30 约数的，可能出现不兼容现象
 * </p>
 *
 */
public class DefaultDayShift implements TimeInventoryCreatePolicy {

    /**
     * 前闭后开 [09:00, 23:00)
     * 【未约】起始时间
     */
    private static final int START_TIME_FOR_UNUSED = 540;   // 单位分钟 [09时]
    private static final int END_TIME_FOR_UNUSED = 1380;    // 单位分钟 [23时]
    private static final int WHOLE_DAY_IN_MINUTES = 1440;   // 一天 1440 分钟
    private static final int STATE_BITS = 2;
    private static final int POSITIONS_PRE_INTEGER = Integer.SIZE / STATE_BITS;

    @Override
    public int name() {
        return 2;
    }

    @Override
    public TimeInventoryCreateValues create(DalParam delParam) {
        TimeInventoryCreateParam param = (TimeInventoryCreateParam) delParam;
        TimeInventoryCreateValues values = new TimeInventoryCreateValues();

        int unitMinutes = TimeConvert.getUnitMinutes(param.getTimeUnit());
        int currentMinutes = 0;
        int count = 0;
        for (int i=0;currentMinutes < WHOLE_DAY_IN_MINUTES;i++) {
            if (START_TIME_FOR_UNUSED <= currentMinutes && currentMinutes < END_TIME_FOR_UNUSED) {
                setValues(values, i, ByteState.STATE_UNUSED.getState());
            } else {
                setValues(values, i, ByteState.STATE_DISABLED.getState());
            }
            count +=1;
            currentMinutes += unitMinutes;
        }
        System.out.println(count);
        return values;
    }

    private void setValues(TimeInventoryCreateValues values, int position, int state) {
        switch (position / POSITIONS_PRE_INTEGER) {
            case 0:
                values.value0 |= (state << leftSwitchOfBits(position));
                break;
            case 1:
                values.value1 |= (state << leftSwitchOfBits(position));
                break;
            case 2:
                values.value2 |= (state << leftSwitchOfBits(position));
                break;
            case 3:
                values.value3 |= (state << leftSwitchOfBits(position));
                break;
            case 4:
                values.value4 |= (state << leftSwitchOfBits(position));
                break;
            case 5:
                values.value5 |= (state << leftSwitchOfBits(position));
            case 6:
                values.value6 |= (state << leftSwitchOfBits(position));
            case 7:
                values.value7 |= (state << leftSwitchOfBits(position));
            default:
                return;
        }
    }

    private int leftSwitchOfBits(int position) {
        return (position % POSITIONS_PRE_INTEGER) * STATE_BITS;
    }

}
