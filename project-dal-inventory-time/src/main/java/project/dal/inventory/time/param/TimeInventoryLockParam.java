//package project.dal.inventory.time.param;
//
//import com.helijia.pivot.inventory.model.TimeInventoryItemModel;
//import com.helijia.pivot.inventory.model.TimeInventoryModel;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//
///**
// *
// * @author jinli Mar 4, 2018
// */
//public class TimeInventoryLockParam extends BaseTimeInventoryLockOpParam {
//
//    private static final long serialVersionUID = 1L;
//
//    public static final int POLICY_ORIGIN = 0;
//    public static final int POLICY_HOME_ORIGIN = 1;
//    public static final int POLICY_STORE_ORIGIN = 2;
//    public static final int POLICY_MEDC_ORIGIN = 3;
//    public static final int DEFAULT_POLICY = POLICY_ORIGIN;
//
//    public static final int INFINITE_REPEAT_LOCK_TIMES = -1;    // 无限制的重复锁操作次数
//    public static final int DEFAULT_REPEAT_LOCK_TIMES = 3;      // 默认的重复锁操作次数限制(同一锁领域对象)
//
//    private TimeInventoryItemModel item;
//    private int amount;                                         // 服务时长(单位: Minutes)
//    private int policy = DEFAULT_POLICY;
//
//    private Integer repeatLockTimes = DEFAULT_REPEAT_LOCK_TIMES;// 同一锁领域对象重复操作锁时间库存的次数
//    private boolean convertUMS = true;                          // 是否需要加工【已约-里程碑】的状态
//    private boolean convertWithRelatedInventories = true;       // 是否需要通过相关联的时间来加工时间段开始&结束的边缘时间
//    private boolean ignoreCurLockScope = false;                 // 是否忽略特定锁对象锁定的时间库存(用于支持改约时间在原预约时间附近)
//    private boolean convertWithCurrentTime = false;             // 是否根据当前时间加工时间库存
//
//    @Override
//    public int state() {
//        return TimeInventoryModel.STATE_USED;
//    }
//
//    public TimeInventoryItemModel getItem() {
//        return item;
//    }
//
//    public void setItem(TimeInventoryItemModel item) {
//        this.item = item;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public int getPolicy() {
//        return policy;
//    }
//
//    public void setPolicy(int policy) {
//        this.policy = policy;
//    }
//
//    public Integer getRepeatLockTimes() {
//        return repeatLockTimes;
//    }
//
//    public void setRepeatLockTimes(Integer repeatLockTimes) {
//        this.repeatLockTimes = repeatLockTimes;
//    }
//
//    public boolean isConvertUMS() {
//        return convertUMS;
//    }
//
//    public void setConvertUMS(boolean convertUMS) {
//        this.convertUMS = convertUMS;
//    }
//
//    public boolean isConvertWithRelatedInventories() {
//        return convertWithRelatedInventories;
//    }
//
//    public void setConvertWithRelatedInventories(boolean convertWithRelatedInventories) {
//        this.convertWithRelatedInventories = convertWithRelatedInventories;
//    }
//
//    public boolean isIgnoreCurLockScope() {
//        return ignoreCurLockScope;
//    }
//
//    public void setIgnoreCurLockScope(boolean ignoreCurLockScope) {
//        this.ignoreCurLockScope = ignoreCurLockScope;
//    }
//
//    public boolean isConvertWithCurrentTime() {
//        return convertWithCurrentTime;
//    }
//
//    public void setConvertWithCurrentTime(boolean convertWithCurrentTime) {
//        this.convertWithCurrentTime = convertWithCurrentTime;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .appendSuper(super.toString())
//                .append("item", item)
//                .append("amount", amount)
//                .append("policy", policy)
//                .append("repeatLockTimes", repeatLockTimes)
//                .append("convertUMS", convertUMS)
//                .append("convertWithRelatedInventories", convertWithRelatedInventories)
//                .append("ignoreCurLockScope", ignoreCurLockScope)
//                .append("convertWithCurrentTime", convertWithCurrentTime)
//                .toString();
//    }
//}
