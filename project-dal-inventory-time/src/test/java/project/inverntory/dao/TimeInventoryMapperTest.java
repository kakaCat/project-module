package project.inverntory.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.dal.inventory.time.api.TimeInventoryViewConverter;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.entity.TimeInventoryViewModel;
import project.dal.inventory.time.metadata.ScopeType;
import project.dal.inventory.time.metadata.TimeUnitState;
import project.dal.inventory.time.repository.db.TimeInventoryMapper;
import project.dal.inventory.time.utils.DateUtil;
import project.inverntory.TimeApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TimeApplication.class)
public class TimeInventoryMapperTest {

        @Autowired
        private TimeInventoryMapper timeInventoryMapper;

        @Autowired
        private TimeInventoryViewConverter timeInventoryViewConverter;


        private static final int scope =2147483647;

        private static final String scopeId ="test";

        @Test
        public void creatTime() {
                TimeInventoryModel timeInventoryModel = new TimeInventoryModel();
                timeInventoryModel.setScope(ScopeType.SCOPE_DEV.getState());
                timeInventoryModel.setScopeId(scopeId);
                timeInventoryModel.setInventoryTime(new Date());
                timeInventoryModel.setTimeUnit(TimeUnitState.UNIT_FULL.getState());
                timeInventoryModel.setValue0(0x00000000);
                timeInventoryModel.setValue1(0xAAAAAAA0);
                timeInventoryModel.setValue2(0x0000000A);
                timeInventoryModel.setValue3(0);
                timeInventoryModel.setValue4(0);
                timeInventoryModel.setValue5(0);
                timeInventoryMapper.insertTimeInventory(timeInventoryModel);
        }

        @Test
        public void getDatesTime() {
                List<Date> list = new ArrayList<>();
                list.add(DateUtil.toDate(new Date()));
                List<TimeInventoryModel> model = timeInventoryMapper.getTimeInventoriesBySeparateDates(scope, scopeId, list);
                System.out.println(model);
        }
//        lockTimeInventories

        @Test
        public void ConverterCatesTime() {

                List<Date> list = new ArrayList<>();
                list.add(DateUtil.toDate(new Date()));
                List<TimeInventoryModel> model = timeInventoryMapper.getTimeInventoriesBySeparateDates(scope, scopeId, list);


                TimeInventoryViewModel convert = timeInventoryViewConverter.convert(model.get(0));
                System.out.println("11");
        }
}
