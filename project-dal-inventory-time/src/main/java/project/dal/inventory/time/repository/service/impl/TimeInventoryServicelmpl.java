package project.dal.inventory.time.repository.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.repository.db.TimeInventoryMapper;
import project.dal.inventory.time.repository.param.QueryTimeParam;
import project.dal.inventory.time.repository.service.TimeInventoryDbService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TimeInventoryServicelmpl implements TimeInventoryDbService {

    @Autowired
    private TimeInventoryMapper timeInventoryMapper;

    @Override
    public void initCreatTimeInventoty(TimeInventoryModel inventory,List<Date> dates) {
        List<TimeInventoryModel> inventories = new ArrayList<>(dates.size());
        for (Date date : dates) {
            try {
                TimeInventoryModel clone = inventory.clone();
                clone.setInventoryTime(date);
                inventories.add(clone);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        timeInventoryMapper.insertTimeInventories(inventories);
    }

    @Override
    public int creatTimeInventoty(TimeInventoryModel inventory) {
        return timeInventoryMapper.insertTimeInventory(inventory);
    }

    @Override
    public List<TimeInventoryModel> batchGetTimeInventoriesByDates(QueryTimeParam param) {

        return timeInventoryMapper.batchGetTimeInventoriesByDates(param.getScope(),param.getScopeIds(),param.getStartDate(),param.getEndDate());
    }

    @Override
    public List<TimeInventoryModel> queryTimeInventories(QueryTimeParam param) {
        return timeInventoryMapper.queryTimeInventories(param.getScope(),param.getScopeIdSingle(),param.getStartDate(),param.getEndDate());
    }


}
