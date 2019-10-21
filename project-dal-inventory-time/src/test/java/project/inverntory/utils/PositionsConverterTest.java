package project.inverntory.utils;

import org.junit.Test;
import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.utils.DateUtil;
import project.dal.inventory.time.utils.PositionsConverter;
import project.inverntory.BaseDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PositionsConverterTest extends BaseDate {

    @Test
    public void testCreate() {
        Date date = DateUtil.parseDateTime("2019-11-00 00:00:00");
        List<TimeInventoryItemModel> items = new ArrayList<>();
        items.add(new TimeInventoryItemModel());


//        String position = PositionsConverter.encodeLockPositions(date, lockParam.getItems(), unit);

//        System.out.println(position);
    }


}
