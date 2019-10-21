package project.dal.inventory.time.api;

import project.dal.inventory.time.entity.TimeInventoryLockModel;
import project.dal.inventory.time.param.TimeInventoryLockParam;
import project.dal.inventory.time.param.TimeInventoryReleaseParam;

import java.util.List;

public interface TimeInventoryFacade {





    TimeInventoryLockModel lockTimeInventories(TimeInventoryLockParam param);


    void releaseTimeInventories(List<TimeInventoryReleaseParam> params);


}
