package project.inverntory.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.dal.inventory.time.api.TimeInventoryService;
import project.dal.inventory.time.metadata.TimeUnitState;
import project.dal.inventory.time.param.TimeInventoryCreateParam;
import project.dal.inventory.time.param.TimeInventoryLockParam;
import project.dal.inventory.time.utils.DateUtil;
import project.inverntory.BaseDate;
import project.inverntory.TimeApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TimeApplication.class)
public class TimeInventoryServiceTest extends BaseDate {



    @Autowired
    private TimeInventoryService timeInventoryService;


    @Test
    public void createTimeInventories(){
        TimeInventoryCreateParam param = new TimeInventoryCreateParam();
        param.setTimeUnit(TimeUnitState.UNIT_FULL.getState());
        param.setPolicy(2);
        param.setScope(scope);
        param.setScopeId(scopeId);
        param.setStartDate(DateUtil.parseDateTime("2019-11-00 00:00:00"));
        param.setEndDate(DateUtil.parseDateTime("2019-11-20 00:00:00"));
        timeInventoryService.createTimeInventories(param);

    }


    @Test
    public void lockTimeInventories(){

        TimeInventoryLockParam param = new TimeInventoryLockParam();
        param.setPolicy(1);
        param.setScope(scope);
        param.setScopeId(scopeId);
        param.setAmount(60);
        param.setOpSource(scope);
        param.setTimeUnit(TimeUnitState.UNIT_FULL.getState());

        timeInventoryService.lockTimeInventories(param);

    }


}
