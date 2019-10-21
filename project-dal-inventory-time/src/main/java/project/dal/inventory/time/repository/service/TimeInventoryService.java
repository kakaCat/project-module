package project.dal.inventory.time.repository.service;

import project.dal.inventory.time.entity.TimeInventoryModel;

import java.util.Date;
import java.util.List;

public interface TimeInventoryService {


    void initCreatTimeInventoty(TimeInventoryModel inventory, List<Date> dates);


    int creatTimeInventoty(TimeInventoryModel inventory);

}
