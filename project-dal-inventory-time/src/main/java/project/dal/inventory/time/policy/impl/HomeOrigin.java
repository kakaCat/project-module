//package project.dal.inventory.time.policy.impl;
//
//
//import org.springframework.util.Assert;
//import project.dal.inventory.time.policy.TimeInventoryLockPolicy;
//
///**
// * 上门锁定时间策略
// */
//public class HomeOrigin implements TimeInventoryLockPolicy {
//
//    public static final int HOME_ORIGIN = 1;
//    private static final int PREVIOUS_LOCK_TIME = 60;   // 路程时间暂时默认为 60 分钟
//
//    @Override
//    public int name() {
//        return 1;
//    }
//
//    @Override
//    public TimeInventoryLockPolicy.TimeInventoryLockValues lock(TimeInventoryLockParam param) {
//
//        TimeInventoryLockValues values = new TimeInventoryLockValues();
//        int unitMinutes = InternalTimeInventoryModel.getUnitMinutes(param.getUnit());
//        values.previous = PREVIOUS_LOCK_TIME / unitMinutes;
//        // 将服务时间作为后锁时间单元的标准，单元数向上取整
//        values.following = param.getAmount() / unitMinutes + (param.getAmount() % unitMinutes != 0 ? 1 : 0);
//        return values;
//    }
//}
