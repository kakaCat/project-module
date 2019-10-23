package project.dal.inventory.time.temp;

import lombok.Data;

import java.util.Date;

/**
 * 技师库存时间－分秒
 */
@Data
public class ArtisanDateDetailed {

    private Integer id;

    /**手艺人时间设置id*/
    private Integer dateId;

    /**手艺人id*/
    private String artisanId;

    /**设置日期*/
    private Date setDate;

    /**设置时间（到时分秒）*/
    private Date setTime;

    /**订单状态 订单状态 无订单：00、 订单：01、订单进行中：02、 无订单，被订单影响：03*/
    private String status;

    /**锁定状态 锁定：00、 未锁定：01*/
    private String lockStatus;

    /**前置空闲时间（以30分钟为单位，空闲时间单位数量）*/
    private Integer beforeRestNo;

    /**后置空闲时间（以30分钟为单位，空闲时间单位数量）*/
    private Integer afterRestNo;

    /**空闲时间（以30分钟为单位，空闲时间单位数量）*/
    private Integer restNo;


}
