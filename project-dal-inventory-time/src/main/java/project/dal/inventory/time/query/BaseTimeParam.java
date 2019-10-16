package project.dal.inventory.time.query;

import lombok.Data;

@Data
public abstract class BaseTimeParam {
    /**
     * @description: //库存对应的领域
     **/
    private Integer scope;
    /**
     * @description: //库存对应的领域模型id
     **/
    private String scopeId;

}
