package project.dal.inventory.time.repository.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dal.inventory.time.entity.TimeInventoryLockModel;
import project.dal.inventory.time.query.TimeInventoryLockQuery;
import project.dal.inventory.time.repository.db.TimeInventoryLockMapper;
import project.dal.inventory.time.repository.service.TimeInventoryLockService;

import java.util.Date;
import java.util.List;

@Service
public class TimeInventoryLockServiceImpl implements TimeInventoryLockService {

    @Autowired
    private TimeInventoryLockMapper timeInventoryLockMapper;


    @Override
    public int createTimeInventoryLock(TimeInventoryLockModel lock) {
        return timeInventoryLockMapper.createTimeInventoryLock(lock);
    }

    @Override
    public TimeInventoryLockModel getTimeInventoryLock(Long id) {
        return timeInventoryLockMapper.getTimeInventoryLock(id);
    }

    @Override
    public List<TimeInventoryLockModel> getTimeInventoryLocks(TimeInventoryLockQuery lockQuery) {
        return null;
    }

    @Override
    public int countTimeInventoryLocks(TimeInventoryLockQuery lockQuery) {
        return 0;
    }

    @Override
    public List<TimeInventoryLockModel> getTimeInventoryLocksByLockScope(Integer lockScope, String lockScopeId, Integer state) {
        return null;
    }

    @Override
    public int countTimeInventoryLocksByLockScope(int lockScope, String lockScopeId) {
        return 0;
    }

    @Override
    public List<TimeInventoryLockModel> getTimeInventoryLocksByScopeAndDates(Integer scope, String scopeId, Integer state, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public int updateTimeInventoryLock(TimeInventoryLockModel lock) {
        return 0;
    }

    @Override
    public int releaseTimeInventoryLock(TimeInventoryLockModel lock) {
        return 0;
    }
}
