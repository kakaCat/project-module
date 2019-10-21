package project.dal.inventory.time.realm;

import lombok.Data;
import project.dal.inventory.time.convert.TimeConvert;
import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.metadata.TimeUnitState;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class TimeInventoryRealm {

    private static final int STATE_BITS = 2;

    private static final int POSITONS_PER_INT = Integer.SIZE / STATE_BITS;

    public TimeInventoryRealm(TimeInventoryModel timeInventoryModel){
        this.timeInventoryModel = timeInventoryModel;
        this.timeUnitState = TimeUnitState.getTimeUnitState(timeInventoryModel.getTimeUnit());

    }

    private TimeInventoryModel timeInventoryModel;

    private TimeUnitState timeUnitState;


    public List<TimeInventoryItemModel> addItems(){
        TimeInventoryModel model = this.timeInventoryModel;
        TimeUnitState timeUnitState = this.timeUnitState;
        List<TimeInventoryItemModel> list = new ArrayList<>(30);
        Calendar cal = Calendar.getInstance();
        cal.setTime(model.getInventoryTime());
        for (int i = 0; i < timeUnitState.getLength(); i++) {
            Date date = cal.getTime();
            int state = getState(i);
            String message = getStateMessage(state);
            list.add(new TimeInventoryItemModel(i, date, state, message));
            cal.add(Calendar.MINUTE, timeUnitState.getUnitValue());
        }
        return list;
    }

//    public List<TimeInventoryItemModel> addpreviousItems(int position){
//        TimeInventoryModel model = this.timeInventoryModel;
//        TimeUnitState timeUnitState = this.timeUnitState;
//        List<TimeInventoryItemModel> list = new ArrayList<>(30);
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(model.getInventoryTime());
//        position = model.
//        for (int i = 0; i < values.previous; i++) {
//            cal.add(Calendar.MINUTE, -timeUnitState.getUnitValue());
//            if (--position < 0) {
//                position = timeUnitState.getLength() - 1;
//            }
//            items.add(0, new TimeInventoryItemModel(position, cal.getTime()));
//        }
//
//
//        return list;
//    }




    /**
     * 获取一天中指定位置的时间库存对应的状态
     *
     * @param position
     * @return 指定时间库存对应的状态
     */
    public int getState(int position) {
        if (position > timeUnitState.getLength()) {
            return 0;
        }
        int state = getValue(position);
        state = state >> ((position % POSITONS_PER_INT) * STATE_BITS) & 0b11;
        return state;
    }

    private int getValue(int position) {
        TimeInventoryModel model = this.timeInventoryModel;
        switch (position / POSITONS_PER_INT) {
            case 0:
                return  model.getValue0();
            case 1:
                return model.getValue1();
            case 2:
                return model.getValue2();
            case 3:
                return model.getValue3();
            case 4:
                return model.getValue4();
            case 5:
                return model.getValue5();
            default:
                return 0;
        }
    }

    private String getStateMessage(int state) {
        switch (state) {
            case 0:
                return "无效";
            case 1:
                return "关闭";
            case 2:
                return "未约";
            case 3:
                return "已约";
            case 4:
                return "已约-里程碑";
            case 5:
                return "被影响";
            default:
                return "";
        }
    }

}
