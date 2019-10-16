package project.dal.inventory.time.param;

import lombok.Data;
import project.dal.inventory.time.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Data
public class TimeInventoryListParam extends BaseParam {

    private static final long serialVersionUID = 1L;

    private Date startDate;     // 时间库存起始时间，前闭后开：[startDate, endDate)
    private Date endDate;       // 时间库存截止时间，前闭后开：[startDate, endDate)

    public List<Date> getDates() {
        List<Date> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(getStartDate());

        while (cal.getTime().getTime() < getEndDate().getTime()) {
            dates.add(cal.getTime());

            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }



}
