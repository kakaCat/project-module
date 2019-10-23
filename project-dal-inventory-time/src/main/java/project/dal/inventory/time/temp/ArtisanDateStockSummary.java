package project.dal.inventory.time.temp;

import lombok.Data;

import java.util.List;

/**
 * 时间库存
 *
 */
@Data
public class ArtisanDateStockSummary {
    /** 描述是否可约 */
    private String desc;
    /** 一天的时间库存一个String  */
    private List<String> times;
    /** ？ */
    private String days;
    /** 当前年月日 */
    private String date;


}
