package project.dal.inventory.time.param;


import lombok.Data;

@Data
public abstract class BaseTimeInventoryLockOpParam extends BaseTimeInventoryStateOpParam {

    public static final Integer LOCK_SCOPE_DEV = Integer.MAX_VALUE;     // 锁记录领域: DEV
    public static final Integer LOCK_SCOPE_ORDER = 1;                   // 锁记录领域: 订单
    public static final Integer LOCK_SCOPE_RESERVE = 2;                 // 锁记录领域: 预约
    public static final Integer DEFAULT_LOCK_SCOPE = LOCK_SCOPE_ORDER;  // 默认锁记录领域

    public static final String LOCK_SCOPE_ID_DEV = "Test For DEV";
    /**
     * @description: //锁记录对应的领域
     **/
    private Integer lockScope = DEFAULT_LOCK_SCOPE;
    /**
     * @description: //锁记录对应的领域id
     **/
    private String lockScopeId;




}
