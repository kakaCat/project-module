package project.dal.inventory.time.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 时间库存操作记录模型
 *
 * 用于记录用户 & 手艺人 & 客服等对特定领域对象时间库存的操作记录
 * 仅支持 insert & select ，未来也不考虑支持 update 。
 *
 */
@Data
public class TimeInventoryOpModel extends BaseModel  {

    /**
     * @description: //库存对应的领域
     **/
    private Integer scope;
    /**
     * @description: //库存对应的领域模型id
     **/
    private String scopeId;

    /**
     * @description: //刻度单位
     **/
    private int timeUnit;
    /**
     * @description: //预约时间
     **/
    private Date inventoryTime;

    /**
     * @description: //锁快照
     **/
    private String positions;
    /**
     * @description: //本次操作导致的最终状态 (与 TimeInventoryModel.state 保持一致)
     **/
    private int state;

    /**
     * @description: //操作来源
     * {@link project.dal.inventory.time.metadata.OpType}
     **/
    private int opSource;
    /**
     * @description: //与 positions 对应，作为解码后的实际操作过的时间单元
     **/
    private List<TimeInventoryItemModel> items;


}
