package project.dal.inventory.time.repository.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.entity.TimeInventoryOpModel;
import project.dal.inventory.time.metadata.ByteState;
import project.dal.inventory.time.param.BaseTimeInventoryStateOpParam;
import project.dal.inventory.time.param.TimeInventoryLockParam;
import project.dal.inventory.time.realm.TimeInventoryRealm;
import project.dal.inventory.time.repository.db.TimeInventoryMapper;
import project.dal.inventory.time.repository.service.TimeInventoryService;
import project.dal.inventory.time.utils.DateUtil;
import project.dal.inventory.time.utils.PositionsConverter;

import java.util.*;

@Service
@Slf4j
public class TimeInventoryServicelmpl implements TimeInventoryService {

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




}
