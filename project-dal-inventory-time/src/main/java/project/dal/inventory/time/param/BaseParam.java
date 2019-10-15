package project.dal.inventory.time.param;

import lombok.Data;
import project.dal.inventory.time.metadata.TimeUnitState;

import java.io.Serializable;


@Data
public abstract class BaseParam implements DalParam,Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * @description: //库存对应领域
     **/
    private int scope;
    /**
     * @description: //库存对应领域模型ID
     **/
    private String scopeId;
    /**
     * @description: //时间库存粒度
     **/
    private int timeUnit = 1;

    public TimeUnitState getTimeUnit(){
        return TimeUnitState.getTimeUnitState(timeUnit);
    }



}
