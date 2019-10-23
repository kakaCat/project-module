package project.dal.inventory.time.temp;

import lombok.Data;

import java.util.Date;


@Data
public class ArtisanDateStockDetailVo {



    private Date   date;                            // 时间
//    private String time;                            // 时间字符串，如 “12:30”
    private String state;                           // 状态0：暂不可约，1: 可预约，2：已约满
    
    public ArtisanDateStockDetailVo() {
		super();
	}

	public ArtisanDateStockDetailVo(Date date, String status) {
		this.state = status;
//		this.time = date;// DateUtil.formatDate(date, "HH:mm");
		this.date = date;
	}


}
