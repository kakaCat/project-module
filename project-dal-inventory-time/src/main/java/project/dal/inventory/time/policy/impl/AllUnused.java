//package project.dal.inventory.time.policy.impl;
//
//import com.helijia.pivot.inventory.model.InternalTimeInventoryModel;
//import com.helijia.pivot.inventory.param.TimeInventoryCreateParam;
//import com.helijia.pivot.inventory.spi.TimeInventoryCreatePolicy;
//
///**
// *
// * @author jinli Mar 3, 2018
// */
//public class AllUnused implements TimeInventoryCreatePolicy {
//
//    public static final int VALUE_ALL_UNUSED = 0xAAAAAAAA;
//    public static final int VALUE_ALL_UNUSED_FOR_UNIT_FULL = 0x0000AAAA;
//
//    @Override
//    public int name() {
//        return 1;
//    }
//
//    @Override
//    public TimeInventoryCreateValues create(TimeInventoryCreateParam param) {
//        TimeInventoryCreateValues values = new TimeInventoryCreateValues();
//        int positionLength = InternalTimeInventoryModel.getPositionLength(param.getUnit());
//        values.value0 = createValue(positionLength);
//        positionLength -= 16;       // each INT can hold 16 unit status
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
//    protected int createValue(Integer positionLength) {
//        if (positionLength >= 16) {
//            return VALUE_ALL_UNUSED;
//        } else if (positionLength == 8) {
//            return VALUE_ALL_UNUSED_FOR_UNIT_FULL;
//        } else {
//            return 0;
//        }
//    }
//
//}
