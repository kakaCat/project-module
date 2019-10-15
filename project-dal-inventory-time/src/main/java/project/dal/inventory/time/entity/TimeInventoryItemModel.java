package project.dal.inventory.time.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class TimeInventoryItemModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @description: //代表一天中，第几个库存，从0开始
     **/
    private int position;
    /**
     * @description: //库存时间
     **/
    private Date date;
    /**
     * @description: //库存状态
     **/
    private int state;
    /**
     * @description: //库存状态对应的描述
     **/
    private String stateMessage;

    public TimeInventoryItemModel() {
    }

    public TimeInventoryItemModel(int position, Date date) {
        this(position, date, 0, null);
    }

    public TimeInventoryItemModel(int position, Date date, int state, String stateMessage) {
        this.position = position;
        this.date = date;
        this.state = state;
        this.stateMessage = stateMessage;
    }




}
