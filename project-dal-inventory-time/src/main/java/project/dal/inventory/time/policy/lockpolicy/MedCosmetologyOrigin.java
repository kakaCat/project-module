//package project.dal.inventory.time.policy.impl;
//
//import com.helijia.pivot.inventory.param.TimeInventoryLockParam;
//import com.helijia.pivot.inventory.spi.TimeInventoryLockPolicy;
//
///**
// * 医疗美容 (medical cosmetology)
// * 医美锁定策略的实现
// * 目前医美业务不锁时间，故
// *  previous = 0;
// *  following = 0;
// *
// * @author fangfeng
// * @since 2018/3/30
// */
//public class MedCosmetologyOrigin implements TimeInventoryLockPolicy {
//
//    @Override
//    public int name() {
//        return 3;
//    }
//
//    @Override
//    public TimeInventoryLockValues lock(TimeInventoryLockParam param) {
//        TimeInventoryLockValues values = new TimeInventoryLockValues();
//        values.previous = 0;
//        values.following = 0;
//        return values;
//    }
//}
