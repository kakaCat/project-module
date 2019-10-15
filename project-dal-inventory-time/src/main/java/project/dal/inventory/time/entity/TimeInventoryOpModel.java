//package project.dal.inventory.time.entity;
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
///**
// * 时间库存操作记录模型
// *
// * 用于记录用户 & 手艺人 & 客服等对特定领域对象时间库存的操作记录
// * 仅支持 insert & select ，未来也不考虑支持 update 。
// *
// * @author fangfeng
// * @since 2018/4/11
// */
//public class TimeInventoryOpModel implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 此处的静态常量需要与 {@link com.helijia.pivot.inventory.param.BaseTimeInventoryStateOpParam} 保持一致
//     */
//    public static final int OP_SOURCE_ARTISAN = 1;              // 手艺人
//    public static final int OP_SOURCE_ORDER = 2;                // 订单
//    public static final int OP_SOURCE_CUSTOMER_SERVICE = 3;     // 客服
//    public static final int OP_SOURCE_SYSTEM = 4;               // 系统
//    public static final int OP_SOURCE_SHIFT = 5;                // 排班 [限本项目内使用]
//    public static final int OP_SOURCE_MANAGER = 6;              // 管理员|店长 [限邻里店使用]
//    public static final int OP_SOURCE_TRANSFER = 7;             // 迁移老时间库存的操作记录 [限迁移使用]
//
//    private long id;                            // 操作日志 id
//    private Date createTime;                    // 操作发生的时间 (失误, 建表的时候写成了 create_time)
//    private Integer scope;                      // 库存对应的领域
//    private String scopeId;                     // 库存对应的领域模型id
//    private String positions;                   // 时间记录 格式 3,5,10...
//    private int unit;                           // 刻度单位 (与 TimeInventoryModel.unit 保持一致)
//    private Date date;                          // 操作的具体日期 YY-MM-dd 00:00:00
//    private int state;                          // 本次操作导致的最终状态 (与 TimeInventoryModel.state 保持一致)
//    private int opSource;                       // 操作来源
//    private List<TimeInventoryItemModel> items; // 与 positions 对应，作为解码后的实际操作过的时间单元
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Integer getScope() {
//        return scope;
//    }
//
//    public void setScope(Integer scope) {
//        this.scope = scope;
//    }
//
//    public String getScopeId() {
//        return scopeId;
//    }
//
//    public void setScopeId(String scopeId) {
//        this.scopeId = scopeId;
//    }
//
//    public String getPositions() {
//        return positions;
//    }
//
//    public void setPositions(String positions) {
//        this.positions = positions;
//    }
//
//    public int getUnit() {
//        return unit;
//    }
//
//    public void setUnit(int unit) {
//        this.unit = unit;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public int getState() {
//        return state;
//    }
//
//    public void setState(int state) {
//        this.state = state;
//    }
//
//    public int getOpSource() {
//        return opSource;
//    }
//
//    public void setOpSource(int opSource) {
//        this.opSource = opSource;
//    }
//
//    public List<TimeInventoryItemModel> getItems() {
//        return items;
//    }
//
//    public void setItems(List<TimeInventoryItemModel> items) {
//        this.items = items;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .append("id", id)
//                .append("createTime", createTime)
//                .append("scope", scope)
//                .append("scopeId", scopeId)
//                .append("positions", positions)
//                .append("unit", unit)
//                .append("date", date)
//                .append("state", state)
//                .append("opSource", opSource)
//                .append("items", items)
//                .toString();
//    }
//}
