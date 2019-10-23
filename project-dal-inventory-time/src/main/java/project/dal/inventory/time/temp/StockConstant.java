package project.dal.inventory.time.temp;


import java.util.*;

/**
 * Created by kaixinguo on 16/6/13.
 */
public class StockConstant {

	/**手艺人时间管理版本*/
	/**手艺人时间管理版本  老版本：00*/
	public final static String ARTISAN_DATEVERSION_OLD = "00";
	/**手艺人时间管理版本  新版本：01*/
	public final static String ARTISAN_DATEVERSION_NEW = "01";

    /**是否全天休息    否：0、 是:1*/
    /**是否全天休息	是:1*/
    public final static String ARTISAN_DATE_ISRESTALLDAY_YES = "1";
    /**是否全天休息	否：0*/
    public final static String ARTISAN_DATE_ISRESTALLDAY_NO = "0";

    /**前台操作类型   0代表点击保存操作   1代表操作全天休息开关*/
    /**点击保存操作:0*/
    public final static String ARTISAN_DATE_IS_SWITCH_0 = "0";
    /**操作全天休息开关:1*/
    public final static String ARTISAN_DATE_IS_SWITCH_1 = "1";

    /**是否有订单	否：0、 是:1*/
    /**是否有订单	是:1*/
    public final static String ARTISAN_DATE_ISORDER_YES = "1";
    /**是否有订单	否：0*/
    public final static String ARTISAN_DATE_ISORDER_NO = "0";

    /**订单状态 无订单：00、 订单：01、订单进行中：02、 无订单，被订单影响：03*/
    /**订单状态 无订单,不被订单影响：00*/
    public final static String ARTISAN_DATE_DETAILED_STATUS_00 = "00";
    /**订单状态 订单：01*/
    public final static String ARTISAN_DATE_DETAILED_STATUS_01 = "01";
    /**订单状态 订单进行中：02*/
    public final static String ARTISAN_DATE_DETAILED_STATUS_02 = "02";
    /**订单状态 无订单，被订单影响：03*/
    public final static String ARTISAN_DATE_DETAILED_STATUS_03 = "03";
    /**订单状态 订单影响（路上时间）*/
    public final static String ARTISAN_DATE_DETAILED_STATUS_04 = "04";

    /**锁定状态 锁定：00、 未锁定：01*/
    /**锁定状态 锁定：00*/
    public final static String ARTISAN_DATE_DETAILED_LOCKSTATUS_00 = "00";
    /**锁定状态 未锁定：01*/
    public final static String ARTISAN_DATE_DETAILED_LOCKSTATUS_01 = "01";
    /**锁定状态 一对多项目锁定(服务时间 + 影响时间)：02*/
    public final static String ARTISAN_DATE_DETAILED_LOCKSTATUS_02 = "02";
    /**
     * 锁定状态 一对多锁定 + 未锁定
     * 02状态 + 01状态
     */
    public final static String ARTISAN_DATE_DETAILED_LOCKSTATUS_03 = "03";

    /**当前类型， 0过期不可点，1订单不可点，2订单影响锁定不可点，3自动锁定为休可点击为接单，4 接单状态可点击为休息
     * 5 当前时间2小时内锁定状态，不可解锁 */
    /**当前类型， 0过期不可点*/
    public final static String ARTISAN_DATE_VIEW_STATUS_0 = "0";
    /**当前类型， 1订单不可点*/
    public final static String ARTISAN_DATE_VIEW_STATUS_1 = "1";
    /**当前类型， 2订单进行中*/
    public final static String ARTISAN_DATE_VIEW_STATUS_2 = "2";
    /**当前类型，3 锁定状态   */
    public final static String ARTISAN_DATE_VIEW_STATUS_3 = "3";
    /**当前类型，4 可以下单，清闲状态*/
    public final static String ARTISAN_DATE_VIEW_STATUS_4 = "4";
    /**当前类型，5 当前时间2小时内锁定状态，不可解锁*/
    public final static String ARTISAN_DATE_VIEW_STATUS_5 = "5";
    /**当前类型，6 被订单影响*/
    public final static String ARTISAN_DATE_VIEW_STATUS_6 = "6";
    //11:一对多服务时段，
    public final static String ARTISAN_DATE_VIEW_STATUS_11 = "11";
    //12:一对多影响时段
    public final static String ARTISAN_DATE_VIEW_STATUS_12 = "12";
    /**客户预约显示状态，0：锁定、   1：未锁定 可预约、 2：已预约*/
    /**客户预约显示状态，0：锁定*/
    public final static String ARTISAN_CUSTOMER_VIEW_STATUS_0 = "0";
    /**客户预约显示状态，1：未锁定 可预约*/
    public final static String ARTISAN_CUSTOMER_VIEW_STATUS_1 = "1";
    /**客户预约显示状态，2：已预约*/
    public final static String ARTISAN_CUSTOMER_VIEW_STATUS_2 = "2";
    /**客户预约显示状态，3：已过期*/
    public final static String ARTISAN_CUSTOMER_VIEW_STATUS_3 = "3";

    /**0:关  非全部休息  可以开，1:关  非全部休息   不可以开，2:开  肯定可以关*/
    /**0 关  非全部休息  可以开*/
    public final static String ARTISAN_VIEW_ALL_DAY_SWITCH_STATUS_0 = "0";
    /**1:关  非全部休息   不可以开*/
    public final static String ARTISAN_VIEW_ALL_DAY_SWITCH_STATUS_1 = "1";
    /**2:开  肯定可以关*/
    public final static String ARTISAN_VIEW_ALL_DAY_SWITCH_STATUS_2 = "2";

    /**距离当前是2小时内锁定不可解锁，小时数量*/
    public final static int ARTISAN_DEFAULT_LOCKTIME_HOUR_FORNOW = 2;

    /**随叫随到预约时距离当前是2小时内锁定不可解锁，小时数量*/
    public final static int ARTISAN_DEFAULT_LOCKTIME_HOUR_FORNOW_ANYTIME = 1;

    //技师匠星等级
    private static Map<Integer, String> ARTISAN_LEVEL_MAP = new HashMap<Integer, String>();
    static{
        ARTISAN_LEVEL_MAP.put(0, "暂未评定");
        ARTISAN_LEVEL_MAP.put(1, "合格");
        ARTISAN_LEVEL_MAP.put(2, "合格");
        ARTISAN_LEVEL_MAP.put(3, "合格");
        ARTISAN_LEVEL_MAP.put(4, "出色");
        ARTISAN_LEVEL_MAP.put(5, "出色");
        ARTISAN_LEVEL_MAP.put(6, "优秀");
        ARTISAN_LEVEL_MAP.put(7, "优秀");
        ARTISAN_LEVEL_MAP.put(8, "卓越");
        ARTISAN_LEVEL_MAP.put(9, "卓越");
        ARTISAN_LEVEL_MAP.put(10, "顶级");
    }
//    public static String getArtisanLevelValue(short level, short glory){
//        return level == 10 && glory == 1 ? "梦幻" : getArtisanLevelValue(level);
//    }
//    public static String getArtisanLevelValue(int level){
//        String value = ARTISAN_LEVEL_MAP.get(level);
//        return StringUtils.isBlank(value) ? ARTISAN_LEVEL_MAP.get(0) : value;
//    }
    /**
     * 获取手艺人时间状态对应app显示的状态
     * @return
     */
    public static Map<String, String> getArtisanDateViweStatusMap(){
        Map<String, String> result = new LinkedHashMap<String, String>();
        result.put(ARTISAN_DATE_DETAILED_STATUS_00, ARTISAN_DATE_VIEW_STATUS_3);
        result.put(ARTISAN_DATE_DETAILED_STATUS_01, ARTISAN_DATE_VIEW_STATUS_1);
        result.put(ARTISAN_DATE_DETAILED_STATUS_02, ARTISAN_DATE_VIEW_STATUS_2);
        result.put(ARTISAN_DATE_DETAILED_STATUS_03, ARTISAN_DATE_VIEW_STATUS_4);
        result.put(ARTISAN_DATE_DETAILED_STATUS_04, ARTISAN_DATE_VIEW_STATUS_4);
        return result;
    }

    /**
     * 获取客户预约手艺人时对应app显示状态
     * @return
     */
    public static Map<String, String> getArtisanCustomerDateViewStatusMap() {
        Map<String, String> result = new LinkedHashMap<String, String>();
        result.put(ARTISAN_DATE_DETAILED_STATUS_01, ARTISAN_CUSTOMER_VIEW_STATUS_2);
        result.put(ARTISAN_DATE_DETAILED_STATUS_02, ARTISAN_CUSTOMER_VIEW_STATUS_0);
        result.put(ARTISAN_DATE_DETAILED_STATUS_03, ARTISAN_CUSTOMER_VIEW_STATUS_0);
        result.put(ARTISAN_DATE_DETAILED_STATUS_00, ARTISAN_CUSTOMER_VIEW_STATUS_1);
        result.put(ARTISAN_DATE_DETAILED_STATUS_04, ARTISAN_CUSTOMER_VIEW_STATUS_0);
        return result;
    }

    /**
     * 用户端3.1 获取客户预约手艺人时对应app显示状态
     * @return
     */
    public static Map<String, String> getArtisanCustomerDateStockViewStatusMap() {
        Map<String, String> result = new LinkedHashMap<>();
        result.put(ARTISAN_DATE_DETAILED_STATUS_01, ARTISAN_CUSTOMER_VIEW_STATUS_2);
        result.put(ARTISAN_DATE_DETAILED_STATUS_02, ARTISAN_CUSTOMER_VIEW_STATUS_2);
        result.put(ARTISAN_DATE_DETAILED_STATUS_03, ARTISAN_CUSTOMER_VIEW_STATUS_2);
        result.put(ARTISAN_DATE_DETAILED_STATUS_00, ARTISAN_CUSTOMER_VIEW_STATUS_1);
        result.put(ARTISAN_DATE_DETAILED_STATUS_04, ARTISAN_CUSTOMER_VIEW_STATUS_2);
        return result;
    }
    /**老版本手艺人时间颗粒度(单位：分)*/
    public final static int ARTISAN_DATE_GRANULARITY_OLD = 60;

    /**手艺人时间颗粒度(单位：分)*/
    public final static int ARTISAN_DATE_GRANULARITY = 30;

    /**作品完成的前置时间段   2表示2*30=1个小时*/
    public final static int ARTISAN_DATE_BEFOREREST_DEFAULT_NO = 2;

    /**当前时间2个小时内不可预约*/
    public final static int ARTISAN_DATE_CONNOT_APPOINTMENT_HOUR = 2;

    /**作品默认120分钟完成*/
    public final static int PRODUCT_DEFAULT_WORK_TIME = 120;

    /**订单影响前面的时间数量*/
    public final static int ARTISAN_DATE_BEFORE_ZJ_RESTNO = 3;

    /**订单影响后面的时间数量*/
    public final static int ARTISAN_DATE_AFTER_ZJ_RESTNO = 2;

    /**顾客预约时间最多7天*/
    public final static int ARTISAN_DATE_BESPOKE_MAXDAY = 7;

    /**顾客预约时间最多30天*/
    public final static int ARTISAN_DATE_BESPOKE_MAXDAY_30 = 30;

    /**顾客预约时间最多4天, 老版本*/
    public final static int ARTISAN_DATE_BESPOKE_MAXDAY_OLD = 4;

    public final static int PRODUCK_VISIT_ON = 1;
    public final static int PRODUCK_VISIT_OFF = 0;

    public final static String SERVICE_ADDRESS_TYPE_HOME = "home"; //上门
    public final static String SERVICE_ADDRESS_TYPE_STORE_SELF = "store_self"; //到店

    public final static int OPERATION_MODITY_CHECK_TRUE = -1; //修改时间库存次数不限制

    public final static int OPERATION_MODITY_TIMES_DEFAULT = 3; //修改时间库存默认次数限制

    public final static int STOCK_BIZ_TYPE_ARTISAN = 1; //手艺人时间库存
    public final static int STOCK_BIZ_TYPE_SHOP = 2; //邻里店时间库存

    public final static int STOCK_RESERVE_TIME_LOG_TYPE_NO_ASTRICT = 0; // 日志不受限

    public final static int STOCK_RESERVE_TIME_LOG_TYPE_ASTRICT = 1; //日志受限

    public static final String NAMESPACE = "time_stock";
    public static final String APP = "time_stock";
    public static final long INVALID_TIME =  1000 * 10;
    public static final String PRODUCT_RESOURCE_CONFIG_KEY = "productResourceConfig";
    public static final String PRODUCT_INSTRUMENT_AFTER_AMOUNT_KEY = "afterAmount";//到店设备服务的后置时间（清理时间，5分钟）
    public static final String PRODUCT_HOME_AFTER_AMOUNT_KEY = "shopAfterAmount";//上门服务的后置时间（路上时间，30分钟）
    public static final String PRODUCT_HOME_BEFORE_AMOUNT_KEY = "shopBeforeAmount";//上门服务的前置置时间（路上时间，30分钟）





    /**
     * 获取默认休息时间
     * @return
     */
    public static List<String> defaultRestDateStr() {
        List<String> result = new ArrayList<String>();
        result.add("00:00");
        result.add("00:30");
        result.add("01:00");
        result.add("01:30");
        result.add("02:00");
        result.add("02:30");
        result.add("03:00");
        result.add("03:30");
        result.add("04:00");
        result.add("04:30");
        result.add("05:00");
        result.add("05:30");
        result.add("06:00");
        result.add("06:30");
        result.add("07:00");
        result.add("07:30");
        result.add("08:00");
        result.add("08:30");
        result.add("09:00");
        result.add("09:30");
        result.add("21:30");
        result.add("22:00");
        result.add("22:30");
        result.add("23:00");
        result.add("23:30");
        return result;
    }

    public static List<String> getDefaultCanProduct(){
        List<String> result = new ArrayList<String>();
        result.add("10:00");
        result.add("10:30");
        result.add("11:00");
        result.add("11:30");
        result.add("12:00");
        result.add("12:30");
        result.add("13:00");
        result.add("13:30");
        result.add("14:00");
        result.add("14:30");
        result.add("15:00");
        result.add("15:30");
        result.add("16:00");
        result.add("16:30");
        result.add("17:00");
        result.add("17:30");
        result.add("18:00");
        result.add("18:30");
        result.add("19:00");
        result.add("19:30");
        result.add("20:00");
        result.add("20:30");
        result.add("21:00");
        return result;
    }

    public static List<String> getAllDateStr() {
        List<String> result = new ArrayList<String>();
        result.add("00:00");
        result.add("00:30");
        result.add("01:00");
        result.add("01:30");
        result.add("02:00");
        result.add("02:30");
        result.add("03:00");
        result.add("03:30");
        result.add("04:00");
        result.add("04:30");
        result.add("05:00");
        result.add("05:30");
        result.add("06:00");
        result.add("06:30");
        result.add("07:00");
        result.add("07:30");
        result.add("08:00");
        result.add("08:30");
        result.add("09:00");
        result.add("09:30");
        result.add("10:00");
        result.add("10:30");
        result.add("11:00");
        result.add("11:30");
        result.add("12:00");
        result.add("12:30");
        result.add("13:00");
        result.add("13:30");
        result.add("14:00");
        result.add("14:30");
        result.add("15:00");
        result.add("15:30");
        result.add("16:00");
        result.add("16:30");
        result.add("17:00");
        result.add("17:30");
        result.add("18:00");
        result.add("18:30");
        result.add("19:00");
        result.add("19:30");
        result.add("20:00");
        result.add("20:30");
        result.add("21:00");
        result.add("21:30");
        result.add("22:00");
        result.add("22:30");
        result.add("23:00");
        result.add("23:30");
        return result;
    }
    public static List<String> getOldVersionDefaultCanProduct(){
        List<String> result = new ArrayList<String>();
        result.add("10:00");
        result.add("11:00");
        result.add("12:00");
        result.add("13:00");
        result.add("14:00");
        result.add("15:00");
        result.add("16:00");
        result.add("17:00");
        result.add("18:00");
        result.add("19:00");
        result.add("20:00");
        result.add("21:00");
        return result;
    }


}
