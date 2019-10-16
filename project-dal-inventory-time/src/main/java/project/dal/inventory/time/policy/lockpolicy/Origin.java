//package project.dal.inventory.time.policy.impl;
//
//import com.helijia.pivot.inventory.param.TimeInventoryLockParam;
//import com.helijia.pivot.inventory.spi.TimeInventoryLockPolicy;
//
///**
// *
// * @author jinli Mar 4, 2018
// */
//public class Origin implements TimeInventoryLockPolicy {
//
//    @Override
//    public int name() {
//        return 0;
//    }
//
//    @Override
//    public TimeInventoryLockValues lock(TimeInventoryLockParam param) {
//        TimeInventoryLockValues values = new TimeInventoryLockValues();
//        values.previous = 0;
//        values.following = param.getAmount();
//        return values;
//    }
//
//}
