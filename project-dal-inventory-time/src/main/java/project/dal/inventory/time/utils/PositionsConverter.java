//package project.dal.inventory.time.utils;
//
//import com.helijia.pivot.inventory.model.InternalTimeInventoryModel;
//import com.helijia.pivot.inventory.model.TimeInventoryItemModel;
//import com.helijia.pivot.inventory.model.TimeInventoryModel;
//import org.springframework.util.StringUtils;
//
//import java.util.*;
//
//import static com.helijia.pivot.inventory.common.util.DateUtil.getIntervalDay;
//import static com.helijia.pivot.inventory.common.util.DateUtil.getIntervalMinutes;
//
///**
// * @author fangfeng
// * @since 2018/3/19
// */
//
//public class PositionsConverter {
//
//    private static final String sep = ",";
//
//    /**
//     * positions 构建规则 -3,-2,-1,0,1,2,3,4...
//     * @param baseDate
//     * @param items
//     * @param unit
//     * @return
//     */
//    public static String encodeLockPositions(Date baseDate, List<TimeInventoryItemModel> items, int unit) {
//        // 整理 items 为时间顺序递增
//        Collections.sort(items, new Comparator<TimeInventoryItemModel>() {
//            @Override
//            public int compare(TimeInventoryItemModel o1, TimeInventoryItemModel o2) {
//                return o1.getDate().compareTo(o2.getDate());
//            }
//        });
//
//        StringBuffer buf = new StringBuffer();
//        int unitMinutes = InternalTimeInventoryModel.getUnitMinutes(unit);
//
//        for (int i=0;i<items.size();i++) {
//            if (i!=0) {
//                buf.append(sep);
//            }
//            buf.append(getIntervalMinutes(baseDate, items.get(i).getDate()) / unitMinutes);
//            // FIXME: 临时支持 由于【关闭】 -> 【已约】状态的转换，需要为释放时间库存暂时留存状态 @fangfeng @2018-05-23
//            if (items.get(i).getState() == TimeInventoryModel.STATE_DISABLED) {
//                buf.append("#1");
//            }
//        }
//
//        String positions = buf.toString();
//        return positions;
//    }
//
//    public static List<TimeInventoryItemModel> decodeLockPositions(Date date, String positions, int unit) {
//        // FIXME: 临时支持 后期直接调用 innerDecodePositions(...) 即可 @fangfeng @2018-05-23
//        // return innerDecodePositions(date, positions, unit);
//
//        List<TimeInventoryItemModel> items = new ArrayList<>();
//        if (StringUtils.isEmpty(positions)) {
//            return items;
//        }
//
//        String[] positionList = positions.split(sep);
//        int[] stateList = new int[positionList.length];
//        for (int i=0;i<positionList.length;i++) {
//            if (positionList[i].contains("#")) {
//                stateList[i] = TimeInventoryModel.STATE_DISABLED;
//                positionList[i] = positionList[i].split("#")[0];
//            } else {
//                stateList[i] = TimeInventoryModel.STATE_UNUSED;
//            }
//        }
//
//        // 定位到 Lock 记录中 positions="0" 的时刻
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int minutesOfDay = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
//
//        int unitMinutes = InternalTimeInventoryModel.getUnitMinutes(unit);
//        int positionLength = InternalTimeInventoryModel.getPositionLength(unit);
//        // 当前处理时刻在时间库存记录中的 position
//        int cursorPosition = minutesOfDay / unitMinutes;
//        // 定位到锁记录的最早的时间
//        cal.add(Calendar.MINUTE, unitMinutes * Integer.parseInt(positionList[0]));
//        // 定位到锁记录的最早 position
//        // 可能出现 position < 0 的情况，需将 position 正确转换
//        cursorPosition += Integer.parseInt(positionList[0]);
//        cursorPosition %= positionLength;
//        cursorPosition = ((cursorPosition % positionLength) + positionLength) % positionLength;
//
//        int[] posList = new int[positionList.length];
//        for (int i=0;i<positionList.length;i++) {
//            posList[i] = Integer.parseInt(positionList[i]);
//            if (i != 0) {
//                cal.add(Calendar.MINUTE, unitMinutes * (posList[i] - posList[i-1]));
//                cursorPosition += (posList[i] - posList[i-1]);
//                if (cursorPosition >= positionLength) {
//                    cursorPosition %= positionLength;
//                }
//            }
//
//            TimeInventoryItemModel item = new TimeInventoryItemModel();
//            item.setDate(cal.getTime());
//            item.setPosition(cursorPosition);
//            item.setState(stateList[i]);
//            items.add(item);
//        }
//        return items;
//    }
//
//    public static String encodeOpPositions(Date baseDate, List<TimeInventoryItemModel> items, int unit) {
//        return innerEncodePositions(baseDate, items, unit);
//    }
//
//    public static List<TimeInventoryItemModel> decodeOpPositions(Date date, String positions, int unit) {
//        return innerDecodePositions(date, positions, unit);
//    }
//
//    private static String innerEncodePositions(Date baseDate, List<TimeInventoryItemModel> items, int unit) {
//        // 整理 items 为时间顺序递增
//        Collections.sort(items, new Comparator<TimeInventoryItemModel>() {
//            @Override
//            public int compare(TimeInventoryItemModel o1, TimeInventoryItemModel o2) {
//                return o1.getDate().compareTo(o2.getDate());
//            }
//        });
//
//        StringBuffer buf = new StringBuffer();
//        int positionLength = InternalTimeInventoryModel.getPositionLength(unit);
//
//        for (int i=0;i<items.size();i++) {
//            if (i!=0) {
//                buf.append(sep);
//            }
//            buf.append(items.get(i).getPosition() + getIntervalDay(baseDate, items.get(i).getDate()) * positionLength);
//        }
//
//        String positions = buf.toString();
//        return positions;
//    }
//
//    private static List<TimeInventoryItemModel> innerDecodePositions(Date date, String positions, int unit) {
//        List<TimeInventoryItemModel> items = new ArrayList<>();
//
//        if (StringUtils.isEmpty(positions)) {
//            return items;
//        }
//        String[] positionList = positions.split(sep);
//
//        // 定位到 Lock 记录中 positions="0" 的时刻
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int minutesOfDay = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
//
//        int unitMinutes = InternalTimeInventoryModel.getUnitMinutes(unit);
//        int positionLength = InternalTimeInventoryModel.getPositionLength(unit);
//        // 当前处理时刻在时间库存记录中的 position
//        int cursorPosition = minutesOfDay / unitMinutes;
//        // 定位到锁记录的最早的时间
//        cal.add(Calendar.MINUTE, unitMinutes * Integer.parseInt(positionList[0]));
//        // 定位到锁记录的最早 position
//        // 可能出现 position < 0 的情况，需将 position 正确转换
//        cursorPosition += Integer.parseInt(positionList[0]);
//        cursorPosition %= positionLength;
//        cursorPosition = ((cursorPosition % positionLength) + positionLength) % positionLength;
//
//        int[] posList = new int[positionList.length];
//        for (int i=0;i<positionList.length;i++) {
//            posList[i] = Integer.parseInt(positionList[i]);
//            if (i != 0) {
//                cal.add(Calendar.MINUTE, unitMinutes * (posList[i] - posList[i-1]));
//                cursorPosition += (posList[i] - posList[i-1]);
//                if (cursorPosition >= positionLength) {
//                    cursorPosition %= positionLength;
//                }
//            }
//
//            TimeInventoryItemModel item = new TimeInventoryItemModel();
//            item.setDate(cal.getTime());
//            item.setPosition(cursorPosition);
//            items.add(item);
//        }
//        return items;
//    }
//}
