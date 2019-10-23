package project.dal.inventory.time.temp;

/**
 * 店铺详情页可约时间描述
 *
 * @author 火星 huoxing@helijia.com
 * @date 2016年5月30日
 *
 */
public enum ArtisanServiceTimeEnum {
    AVALAIBLE_TODAY("today", "今日可约"),
    AVALAIBLE_TOMORROW("tomorrow", "明日可约"),
    AVALAIBLE_THREE_DAYS("threeDays", "三日内可约"),
    AVALAIBLE_SEVEN_DAYS("threeDays", "七日内可约"),
    AVALAIBLE_THIRTY_DAYS("thirtyDays", "查看30天"),
    UN_AVALAIBLE("unavailable ", "暂不可约")
    ;

    private String name;
    private String value;

     ArtisanServiceTimeEnum(String name, String value) {
         this.name  = name;
         this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public ArtisanServiceTimeEnum getByName(String name){
        ArtisanServiceTimeEnum[]  elements = ArtisanServiceTimeEnum.values();
        for (ArtisanServiceTimeEnum timeEnum: elements){
            if (name.equals(timeEnum.getName())){
                return timeEnum;
            }
        }
        return null;
    }
}
