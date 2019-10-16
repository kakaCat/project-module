package project.dal.inventory.time.param;


import lombok.Data;
import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.metadata.ByteState;


@Data
public class TimeInventoryLockParam extends BaseTimeInventoryLockOpParam {

    private static final long serialVersionUID = 1L;

    public static final int POLICY_ORIGIN = 0;
    public static final int POLICY_HOME_ORIGIN = 1;
    public static final int POLICY_STORE_ORIGIN = 2;
    public static final int POLICY_MEDC_ORIGIN = 3;
    public static final int DEFAULT_POLICY = POLICY_ORIGIN;

    public static final int INFINITE_REPEAT_LOCK_TIMES = -1;    // 无限制的重复锁操作次数
    public static final int DEFAULT_REPEAT_LOCK_TIMES = 3;      // 默认的重复锁操作次数限制(同一锁领域对象)

    private TimeInventoryItemModel item;
    private int amount;                                         // 服务时长(单位: Minutes)
    private int policy = DEFAULT_POLICY;

    private Integer repeatLockTimes = DEFAULT_REPEAT_LOCK_TIMES;// 同一锁领域对象重复操作锁时间库存的次数
    private boolean convertUMS = true;                          // 是否需要加工【已约-里程碑】的状态
    private boolean convertWithRelatedInventories = true;       // 是否需要通过相关联的时间来加工时间段开始&结束的边缘时间
    private boolean ignoreCurLockScope = false;                 // 是否忽略特定锁对象锁定的时间库存(用于支持改约时间在原预约时间附近)
    private boolean convertWithCurrentTime = false;             // 是否根据当前时间加工时间库存

    @Override
    public int state() {
        return ByteState.STATE_UNUSED.getState();
    }


}
