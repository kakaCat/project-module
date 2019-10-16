package project.dal.inventory.time.query;

import lombok.Data;

import java.util.Date;

@Data
public class TimeInventoryLockQuery implements DalQuery {

    private Date date;                          // 预约时间 与[startDate, endDate) 互斥，不可同时使用
    private Date startDate;                     // 开始时间
    private Date endDate;                       // 结束时间 [startDate, endDate)
    private Integer state;                      // 锁记录状态


    private Integer lockScope;                  // 锁记录对应的领域
    private String lockScopeId;                 // 锁记录对应的领域id


}
