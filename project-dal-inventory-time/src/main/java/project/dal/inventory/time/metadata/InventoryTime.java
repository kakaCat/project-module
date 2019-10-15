package project.dal.inventory.time.metadata;

public final class InventoryTime {


    public static final int STATE_INVALID = 0b00;               // 状态：无效，无意义
    public static final int STATE_DISABLED = 0b01;              // 状态：关闭，不可使用
    public static final int STATE_UNUSED = 0b10;                // 状态：未约，可使用
    public static final int STATE_USED = 0b11;                  // 状态：已约，不可使用


    public static final int UNIT_QUARTER = 1;                   // 刻度单位，代表一个刻度15分钟
    public static final int UNIT_HALF = 2;                      // 刻度单位，代表一个刻度30分钟
    public static final int UNIT_FULL = 3;                      // 刻度单位，代表一个刻度60分钟
    public static final int DEFAULT_UNIT = UNIT_QUARTER;        // 默认单位，15分钟

}
