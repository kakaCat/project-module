package project.dal.inventory.time.convert;

import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.metadata.TimeUnitState;

public final class TimeConvert {

    private static final int STATE_BITS = 2;
    private static final int POSITONS_PER_INT = Integer.SIZE / STATE_BITS;


//    public static int getPositionLength(TimeUnitState unit) {
//        switch (unit) {
//            case UNIT_QUARTER:
//                return 96;
//            case UNIT_HALF:
//                return 48;
//            case UNIT_FULL:
//                return 24;
//            default:
//              return 0;
//        }
//    }
//
//    public static int getUnitMinutes(TimeUnitState unit) {
//        switch (unit) {
//            case UNIT_FIVE:
//                return 5;
//            case UNIT_QUARTER:
//                return 15;
//            case UNIT_HALF:
//                return 30;
//            case UNIT_FULL:
//                return 60;
//            default:
//                return 0;
//        }
//    }
//
//    /**
//     * 获取一天中指定位置的时间库存对应的状态
//     *
//     * @param position
//     * @return 指定时间库存对应的状态
//     */
//    public static int getState(int position, TimeUnitState unit, TimeInventoryModel model) {
//        if (position > getPositionLength(unit)) {
//            return 0;
//        }
//        int state = getValue(position,model);
//        state = state >> ((position % POSITONS_PER_INT) * STATE_BITS) & 0b11;
//        return state;
//    }
//
//    private static int getValue(int position,TimeInventoryModel model) {
//        switch (position / POSITONS_PER_INT) {
//            case 0:
//                return  model.getValue0();
//            case 1:
//                return model.getValue1();
//            case 2:
//                return model.getValue2();
//            case 3:
//                return model.getValue3();
//            case 4:
//                return model.getValue4();
//            case 5:
//                return model.getValue5();
//            default:
//                return 0;
//        }
//    }

}
