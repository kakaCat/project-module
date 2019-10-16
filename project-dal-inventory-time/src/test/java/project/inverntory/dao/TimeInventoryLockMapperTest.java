package project.inverntory.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.dal.inventory.time.entity.TimeInventoryLockModel;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.metadata.ScopeType;
import project.dal.inventory.time.metadata.TimeUnitState;
import project.dal.inventory.time.repository.db.TimeInventoryLockMapper;
import project.dal.inventory.time.utils.DateUtil;
import project.inverntory.TimeApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TimeApplication.class)
public class TimeInventoryLockMapperTest {

        @Autowired
        private TimeInventoryLockMapper timeInventoryLockMapper;

        private static final int scope =2147483647;

        private static final String scopeId ="test";

        @Test
        public void lockTime() {
                TimeInventoryLockModel lock = new TimeInventoryLockModel();
                lock.setScope(ScopeType.SCOPE_DEV.getState());
                lock.setScopeId(scopeId);
                lock.setLockScope(ScopeType.SCOPE_DEV.getState());
                lock.setLockScopeId(scopeId);
                lock.setInventoryTime(new Date());
                lock.setTimeUnit(TimeUnitState.UNIT_FULL.getState());
                lock.setPositions("0");
                timeInventoryLockMapper.createTimeInventoryLock(lock);
        }

        @Test
        public void getDatesTime() {

        }
//        lockTimeInventories
    
}
