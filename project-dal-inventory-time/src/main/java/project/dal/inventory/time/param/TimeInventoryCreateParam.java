package project.dal.inventory.time.param;

import lombok.Data;
import project.dal.inventory.time.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class TimeInventoryCreateParam extends BaseParam {



    public static final int POLICY_ALL_DISABLED = 0;                // 全部关闭 [00:00, 24:00) -> 【关闭】
    public static final int POLICY_ALL_UNUSED = 1;                  // 全部未约 [00:00, 24:00) -> 【未约】
    public static final int POLICY_DEFAULT_DAY_SHIFT = 2;           // 默认白班 [09:00, 23:00) -> 【未约】
    public static final int POLICY_TEMPORARY_DAY_SHIFT = 3;         // 临时白班 [10:00, 21:30) -> 【未约】

    public static final int DEFAULT_POLICY = POLICY_ALL_DISABLED;

    private Date startDate;     // 时间库存起始时间，前闭后开：[startDate, endDate)
    private Date endDate;       // 时间库存截止时间，前闭后开：[startDate, endDate)

    private int policy = DEFAULT_POLICY;

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

    public void setStartDate(Date startDate) {
        this.startDate = DateUtil.toDate(startDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = DateUtil.toDate(endDate);
    }



}
