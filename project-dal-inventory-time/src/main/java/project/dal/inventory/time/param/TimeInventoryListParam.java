//package project.dal.inventory.time.param;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import com.helijia.pivot.inventory.common.util.DateUtil;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//
///**
// *
// * @author jinli Mar 4, 2018
// */
//public class TimeInventoryListParam extends BaseParam {
//
//    private static final long serialVersionUID = 1L;
//
//    private Date startDate;     // 时间库存起始时间，前闭后开：[startDate, endDate)
//    private Date endDate;       // 时间库存截止时间，前闭后开：[startDate, endDate)
//
//    public List<Date> getDates() {
//        List<Date> dates = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(getStartDate());
//
//        while (cal.getTime().getTime() < getEndDate().getTime()) {
//            dates.add(cal.getTime());
//
//            cal.add(Calendar.DATE, 1);
//        }
//        return dates;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = DateUtil.toDate(startDate);
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = DateUtil.toDate(endDate);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .appendSuper(super.toString())
//                .append("startDate", startDate)
//                .append("endDate", endDate)
//                .toString();
//    }
//}
