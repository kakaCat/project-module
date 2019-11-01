package project.dal.inventory.time.biz.context;

import lombok.Data;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.param.TimeInventoryLockParam;
import project.dal.inventory.time.policy.TimeInventoryLockPolicy;

import java.util.Date;
import java.util.List;

@Data
public class QueryTimesContext implements Context {

    private Date startDate;

    private Date endDate;

    private List<TimeInventoryModel> timeInventorys;

    private List<TimeInventoryLockParam>  params;

    private List<TimeInventoryLockPolicy> lockPolicy;

}
