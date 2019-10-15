//package project.dal.inventory.time.policy.impl;
//
//
//import project.dal.inventory.time.param.DalParam;
//import project.dal.inventory.time.policy.TimeInventoryCreatePolicy;
//
//
//public class AllDisabled implements TimeInventoryCreatePolicy {
//
//    public static final int VALUE_ALL_DISABLED = 0x55555555;
//    public static final int VALUE_ALL_DISABLED_FOR_UNIT_FULL = 0x00005555;
//
//    @Override
//    public int name() {
//        return 0;
//    }
//
//    @Override
//    public TimeInventoryCreateValues create(DalParam param) {
//        TimeInventoryCreateValues values = new TimeInventoryCreateValues();
//        int positionLength = InternalTimeInventoryModel.getPositionLength(param.getUnit());
//        values.value0 = createValue(positionLength);
//        positionLength -= 16;   // each INT can hold 16 unit status
//        values.value1 = createValue(positionLength);
//        positionLength -= 16;
//        values.value2 = createValue(positionLength);
//        positionLength -= 16;
//        values.value3 = createValue(positionLength);
//        positionLength -= 16;
//        values.value4 = createValue(positionLength);
//        positionLength -= 16;
//        values.value5 = createValue(positionLength);
//        positionLength -= 16;
//        return values;
//    }
//
//    protected int createValue(int leftPositionLength) {
//        if (leftPositionLength >= 16) {
//            return VALUE_ALL_DISABLED;
//        } else if (leftPositionLength == 8) {
//            return VALUE_ALL_DISABLED_FOR_UNIT_FULL;
//        } else {
//            return 0;
//        }
//    }
//
//}
