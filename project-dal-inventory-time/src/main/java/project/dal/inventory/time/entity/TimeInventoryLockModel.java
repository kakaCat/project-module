package project.dal.inventory.time.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TimeInventoryLockModel extends BaseModel {



//    public static final int STATE_ON = 1;       // 有效状态
//    public static final int STATE_OFF = 0;      // 失效状态


    private String positions;                   // 锁快照
    private List<TimeInventoryItemModel> items; // 锁定的时间库存单元

    private int unit;                           // 刻度单位

    private Date date;                          // 预约时间

    private Integer state;                      // 锁记录状态

    private Integer scope;                      // 库存对应的领域
    private String scopeId;                     // 库存对应的领域模型id
    private Integer lockScope;                  // 锁记录对应的领域
    private String lockScopeId;                 // 锁记录对应的领域id


}
