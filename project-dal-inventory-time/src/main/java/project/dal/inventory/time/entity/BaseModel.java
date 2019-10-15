package project.dal.inventory.time.entity;



import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定义基础模型的要求
 * 
 * @author jinli Mar 3, 2018
 */
@Data
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    /**
     * @description: //创建时间
     **/
    private Date createDate;
    /**
     * @description: //修改时间
     **/
    private Date updateDate;




}
