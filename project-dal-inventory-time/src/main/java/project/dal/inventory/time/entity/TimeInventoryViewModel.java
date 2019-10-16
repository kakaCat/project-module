package project.dal.inventory.time.entity;



import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TimeInventoryViewModel implements Serializable {

    /**
     * 下述状态的描述用于对外展示，包括对用户 & 手艺人 & SSO
     */
    public static final int STATE_INVALID = 0b000;          // 状态: 无效, 无意义
    public static final int STATE_DISABLE = 0b001;          // 状态: 关闭, 不可使用
    public static final int STATE_UNUSED = 0b010;           // 状态: 未约, 可使用
    public static final int STATE_USED = 0b011;             // 状态: 已约, 不可使用
    public static final int STATE_USED_MILESTONE = 0b100;   // 状态: 已约-里程碑, 不可使用 [基于已约状态]
    public static final int STATE_AFFECTED = 0b101;         // 状态: 被影响, 仅供商户端使用 [临时逻辑, 后续会移除]

    private TimeInventoryModel inventory;

    private String unitMessage;

    private List<TimeInventoryItemModel> items;




    private String toHexValues(TimeInventoryModel inventory) {
        return String.format("%#010x %#010x %#010x %#010x %#010x %#010x", inventory.getValue0(), inventory.getValue1(), inventory.getValue2(),
                inventory.getValue3(), inventory.getValue4(), inventory.getValue5());
    }

}