//package project.dal.inventory.time.policy.impl;
//
//import com.helijia.pivot.inventory.model.InternalTimeInventoryModel;
//import com.helijia.pivot.inventory.param.TimeInventoryLockParam;
//import com.helijia.pivot.inventory.spi.TimeInventoryLockPolicy;
//
///**
// * 到店锁定时间策略
// */
//public class StoreOrigin implements TimeInventoryLockPolicy {
//
//    public static final int STORE_ORIGIN = 2;
//
//    @Override
//    public int name() {
//        return 2;
//    }
//
//    @Override
//    public TimeInventoryLockValues lock(TimeInventoryLockParam param) {
//
//        TimeInventoryLockValues values = new TimeInventoryLockValues();
//        values.previous = 0;
//        int unitMinutes = InternalTimeInventoryModel.getUnitMinutes(param.getUnit());
//        values.following = param.getAmount() / unitMinutes + (param.getAmount() % unitMinutes != 0 ? 1 : 0);
//        return values;
//    }
//}
