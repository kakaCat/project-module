package project.dal.inventory.time.param;

import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.utils.DateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class BaseTimeInventoryStateOpParam extends BaseParam {

    private static final long serialVersionUID = 1L;

    private List<TimeInventoryItemModel> items;
    /**
     * @description: //操作类型
     * {@link project.dal.inventory.time.metadata.OpType}
     **/
    private int opSource;

    public abstract int state();

    public List<Date> getDates() {
        Set<Date> dates = new HashSet<>();
        for (TimeInventoryItemModel item : items) {
            Date date = DateUtil.toDate(item.getDate());
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
        List<Date> ret = new ArrayList<>(dates);
        Collections.sort(ret);
        return ret;
    }

    public List<TimeInventoryItemModel> getItems() {
        if (items == null) {
            items = new ArrayList<>(0);
        }
        return items;
    }

    public void setItems(List<TimeInventoryItemModel> items) {
        this.items = items;
    }

    public int getOpSource() {
        return opSource;
    }

    public void setOpSource(int opSource) {
        this.opSource = opSource;
    }


}
