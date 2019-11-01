package project.dal.inventory.time.repository.service;

import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.repository.param.QueryTimeParam;

import java.util.Date;
import java.util.List;

public interface TimeInventoryDbService {


    void initCreatTimeInventoty(TimeInventoryModel inventory, List<Date> dates);


    int creatTimeInventoty(TimeInventoryModel inventory);


    List<TimeInventoryModel> batchGetTimeInventoriesByDates(QueryTimeParam param);

    List<TimeInventoryModel> queryTimeInventories(QueryTimeParam param);


}
