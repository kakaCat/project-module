package project.dal.inventory.time.entity;

import java.util.Date;


import lombok.Data;


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



//    public static final int SCOPE_DEV = Integer.MAX_VALUE;      // 领域: 开发
//    public static final int SCOPE_ARTISAN = 1;                  // 领域: 手艺人
//    public static final int SCOPE_NEIGHBOR_SHOP_CLERK = 2;      // 领域: 邻里店店员
//    public static final int SCOPE_NEIGHBOR_SHOP_DEVICE = 3;     // 领域: 邻里店设备
//    public static final String SCOPE_ID_DEV = "Test For DEV";   // 领域id:

//    public static final int UNIT_QUARTER = 1;                   // 刻度单位，代表一个刻度15分钟
//    public static final int UNIT_HALF = 2;                      // 刻度单位，代表一个刻度30分钟
//    public static final int UNIT_FULL = 3;                      // 刻度单位，代表一个刻度60分钟
//    public static final int DEFAULT_UNIT = UNIT_QUARTER;        // 默认单位，15分钟

    public static final int STATE_INVALID = 0b00;               // 状态：无效，无意义
    public static final int STATE_DISABLED = 0b01;              // 状态：关闭，不可使用
    public static final int STATE_UNUSED = 0b10;                // 状态：未约，可使用
    public static final int STATE_USED = 0b11;                  // 状态：已约，不可使用



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
    private int timeUnit = 1;
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





}
