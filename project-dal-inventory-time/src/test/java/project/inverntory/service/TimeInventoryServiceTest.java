package project.inverntory.service;


import com.helijia.misc.common.utils.ArtisanDateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.dal.inventory.time.api.TimeInventoryService;
import project.dal.inventory.time.api.TimeInventoryViewConverter;
import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.entity.TimeInventoryViewModel;
import project.dal.inventory.time.metadata.LockPolicy;
import project.dal.inventory.time.metadata.TimeUnitState;
import project.dal.inventory.time.param.TimeInventoryCreateParam;
import project.dal.inventory.time.param.TimeInventoryLockParam;
import project.dal.inventory.time.repository.db.TimeInventoryMapper;
import project.dal.inventory.time.temp.ArtisanDateStockSummary;
import project.dal.inventory.time.temp.ArtisanDateStockVo;
import project.dal.inventory.time.utils.DateStockServiceUtil;
import project.dal.inventory.time.utils.DateUtil;
import project.inverntory.BaseDate;
import project.inverntory.TimeApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TimeApplication.class)
public class TimeInventoryServiceTest extends BaseDate {



    @Autowired
    private TimeInventoryService timeInventoryService;
    @Autowired
    private TimeInventoryMapper timeInventoryMapper;
    @Autowired
    private TimeInventoryViewConverter timeInventoryViewConverter;

    @Test
    public void createTimeInventories(){
        TimeInventoryCreateParam param = new TimeInventoryCreateParam();
        param.setTimeUnit(TimeUnitState.UNIT_HALF.getState());
        param.setPolicy(2);
        param.setScope(scope);
        param.setScopeId(scopeId);
        param.setStartDate(DateUtil.parseYYYMMDDHHMMSS("2019-10-23 00:00:00"));
        param.setEndDate(DateUtil.parseYYYMMDDHHMMSS("2019-12-20 00:00:00"));
        timeInventoryService.createTimeInventories(param);

    }


    @Test
    public void lockTimeInventories(){

        Date startDate = DateUtil.parseYYYMMDDHHMMSS("2019-10-23 10:00:00");
        TimeInventoryLockParam param = new TimeInventoryLockParam();
        int index = DateUtil.getDateSubscript(30,startDate);
        TimeInventoryItemModel item = new TimeInventoryItemModel();
        item.setDate(startDate);
        item.setPosition(index);
        param.setItem(item);

        param.setPolicy(1);
        param.setScope(scope);
        param.setScopeId(scopeId);
        param.setLockScope(scope);
        param.setLockScopeId(scopeId);

        param.setAmount(60);
        param.setOpSource(scope);
        param.setTimeUnit(TimeUnitState.UNIT_FULL.getState());
        param.setAmount(120);
        timeInventoryService.lockTimeInventories(param);

    }

    @Test
    public void query(){
        //查询当天时间
        Date startDate = DateUtil.parseYYYYMMDD("2019-10-23");
        Date endDate = DateUtil.parseYYYYMMDD("2019-11-17");
        List<Date> list = new ArrayList<>();
        List<Date> dates = DateUtil.getDates(startDate, endDate);
        list.addAll(dates);
        List<TimeInventoryModel> inventories = timeInventoryMapper.getTimeInventoriesBySeparateDates(scope, scopeId, list);
        //遮盖策略
        TimeInventoryLockParam lockParam = new TimeInventoryLockParam();
        lockParam.setPolicy(LockPolicy.HOME_ORIGIN.getIndex());
        lockParam.setAmount(120);
        lockParam.setTimeUnit(TimeUnitState.UNIT_HALF.getState());
        lockParam.setScopeId(scopeId);
        lockParam.setScope(scope);
        lockParam.setLockScope(TimeInventoryLockParam.LOCK_SCOPE_ORDER);
        lockParam.setLockScopeId(scopeId);
        lockParam.setIgnoreCurLockScope(true);

        //转换
        List<TimeInventoryViewModel> views = timeInventoryViewConverter.convert(inventories, lockParam, false);

        List<ArtisanDateStockVo> stocks = DateStockServiceUtil.queryAllArtisanDate(scopeId, startDate, endDate, views);
//
        ArtisanDateStockSummary artisanDateStockSummary = DateStockServiceUtil.convety2ArtisanDateStockSummary(stocks, startDate, startDate, false);
        System.out.println(111);
    }


}
