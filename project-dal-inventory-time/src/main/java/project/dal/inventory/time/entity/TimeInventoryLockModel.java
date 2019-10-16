package project.dal.inventory.time.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TimeInventoryLockModel extends BaseModel {

    /**
     * @description: //锁快照
     **/
    private String positions;
    /**
     * @description: //锁定的时间库存单元
     **/
    private List<TimeInventoryItemModel> items;

    /**
     * @description: //刻度单位
     **/
    private int timeUnit;
    /**
     * @description: //预约时间
     **/
    private Date inventoryTime;
    /**
     * @description: //锁记录状态
     * {@link project.dal.inventory.time.metadata.LockState}
     **/
    private Integer lockState;
    /**
     * @description: //库存对应的领域
     **/
    private Integer scope;
    /**
     * @description: //库存对应的领域模型id
     **/
    private String scopeId;
    /**
     * @description: //锁记录对应的领域
     **/
    private Integer lockScope;
    /**
     * @description: //锁记录对应的领域id
     **/
    private String lockScopeId;


}
