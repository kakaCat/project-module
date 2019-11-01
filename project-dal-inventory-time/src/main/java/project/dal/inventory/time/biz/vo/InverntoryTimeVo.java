package project.dal.inventory.time.biz.vo;


import lombok.Data;

import java.util.List;

@Data
public class InverntoryTimeVo {

    /** 描述是否可约 */
    private String desc;
    /** 一天的时间库存一个String  */
    private List<String> times;
    /** ？ */
    private String days;
    /** 当前年月日 */
    private String date;


}
