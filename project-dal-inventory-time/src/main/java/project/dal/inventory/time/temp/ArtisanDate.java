package project.dal.inventory.time.temp;

import lombok.Data;

import java.util.Date;

/**
 * 技师库存时间－日期
 */
@Data
public class ArtisanDate {

    private Integer id;

    private String artisanId;

    private Date setDate;

    private String isRestAllDay;

    private String isOrder;


    
}
