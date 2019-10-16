package project.dal.inventory.time.entity;

import java.util.Date;


import lombok.Data;
import project.dal.inventory.time.metadata.TimeUnitState;


/**
 * <pre>
 * 时间库存模型，用于代表每一个时间刻度所对应的状态。
 *
 * 
 * 状态：
 *  {@link TimeInventoryModel#STATE_INVALID}    无效  00
 *  {@link TimeInventoryModel#STATE_DISABLED}   关闭  01
 *  {@link TimeInventoryModel#STATE_UNUSED}     未约  10
 *  {@link TimeInventoryModel#STATE_USED}       已约  11
 * 
 *  状态机：
 *              +-----------+
 *              |           |
 *              V           |
 *  INIT --> DISABLED --> UNUSED --> USED
 *   |                      ^         |
 *   |                      |         |
 *   +----------------------+---------+
 *  通过N（N<=6）个INT型字段，可表达一天24小时的所有状态。
 *  
 *  举例：单位15分钟，6个INT字段表达方式
 *  
 *  value0 前两个字节：
 *  01:45         01:30         01:15         01:00         00:45         00:30         00:15         00:00
 *  01(DISABLED)  01(DISABLED)  11(USED)      11(USED)      10(UNUSED)    10(UNUSED)    01(DISABLED)  01(DISABLED)
 *  
 *  value0 后两个字节：
 *  03:45         03:30         03:15         03:00         02:45         02:30         02:15         02:00
 *  01(DISABLED)  01(DISABLED)  11(USED)      11(USED)      10(UNUSED)    10(UNUSED)    01(DISABLED)  01(DISABLED)
 * 
 *  以此类推，可以表达一天24小时的所有时间库存情况。
 *  之所以使用INT类型，可以有效降低锁粒度，提升性能。
 *  
 *  在这样的设计中，
 *      当粒度为15分钟，则需要24个字节表达一天24小时，一共需要6个INT字节；
 *      当粒度为30分钟，则需要12个字节表达一天24小时，一共需要3个INT字节；
 *      当粒度为60分钟，则需要06个字节表达一天24小时，一共需要2个INT字节；
 * </pre>
 * 
 * @author jinli Mar 3, 2018
 */
@Data
public class TimeInventoryModel extends BaseModel {

    /**
     * @description: //库存所对应的领域
     * {@link project.dal.inventory.time.metadata.ScopeType}
     **/
    private int scope;
    /**
     * @description: //库存所对应的领域模型ID
     **/
    private String scopeId;
    /**
     * @description: //刻度单位
     * {@link project.dal.inventory.time.metadata.TimeUnitState}
     **/
    private int timeUnit = TimeUnitState.UNIT_QUARTER.getState();
    /**
     * @description: //日期
     **/
    private Date inventoryTime;

    private int value0;
    private int value1;
    private int value2;
    private int value3;
    private int value4;
    private int value5;



    public TimeUnitState getTimeUnitState(){
        return TimeUnitState.getTimeUnitState(timeUnit);
    }

}
