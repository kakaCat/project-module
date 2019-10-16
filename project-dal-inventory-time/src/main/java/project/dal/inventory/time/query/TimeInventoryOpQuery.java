package project.dal.inventory.time.query;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TimeInventoryOpQuery implements DalQuery {

    private Integer scope;                      // 库存对应的领域
    private String scopeId;                     // 库存对应的领域模型id

    private Date createTimeLeftRange;           //
    private Date createTimeRightRange;          // createTime in [createTimeLeftRange, createTimeRightRange)

    private Date startDate;                     // 操作影响的开始日期
    private Date endDate;                       // 操作影响的查询结束日期 [startDate, endDate)
    private Date date;                          // 操作影响的具体日期

    private Integer state;                      // 目标状态值
    private List<Integer> states;               // 目标状态数组


}
