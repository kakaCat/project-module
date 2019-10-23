package project.dal.inventory.time.temp;


import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class ArtisanDateStockVo {


    private Date                      date;                           // 日期
    private ArtisanDate               artisanDate;                    // 手艺人时间天设置
    private List<ArtisanDateDetailed> artisanDateDetailed;    // 手艺人时间点设置
    private List<ArtisanDateStockDetailVo> artisanDateStockDetailVo;    // 手艺人时间点设置
    public ArtisanDateStockVo(Date date, ArtisanDate artisanDate, List<ArtisanDateDetailed> artisanDateDetailed) {
        this.date = date;
        this.artisanDate = artisanDate;
        this.artisanDateDetailed = artisanDateDetailed;
    }





}
