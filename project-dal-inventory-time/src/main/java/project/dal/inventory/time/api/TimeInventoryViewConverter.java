//package project.dal.inventory.time.api;
//
//import java.util.List;
//
//import com.helijia.pivot.inventory.model.TimeInventoryItemModel;
//import com.helijia.pivot.inventory.model.TimeInventoryModel;
//import com.helijia.pivot.inventory.model.TimeInventoryViewModel;
//import com.helijia.pivot.inventory.param.TimeInventoryLockParam;
//
///**
// *
// * @author jinli Mar 3, 2018
// */
//public interface TimeInventoryViewConverter {
//
//    /**
//     * 时间库存转换器
//     * 只支持单日的库存转换
//     * @param inventory 时间库存对象
//     * @return 时间库存视图对象
//     */
//    TimeInventoryViewModel convert(TimeInventoryModel inventory);
//
//    /**
//     * 加工时间库存对象列表
//     *
//     * 支持同一领域对象连续若干日的库存转换
//     * 要求时间库存对象列表中对象描述的日期{@link TimeInventoryModel#date}递增
//     * 要求时间库存对象列表中对象描述的领域对象({@link TimeInventoryModel#scope}, {@link TimeInventoryModel#scopeId}) 一致
//     * 要求时间库存对象列表中对象描述的时间粒度{@link TimeInventoryModel#unit} 一致
//     *
//     * 需要注意的是，加工后的时间库存项({@link TimeInventoryItemModel})将包含五种可能的状态:
//     * <ul>
//     * <li>
//     *  {@link TimeInventoryViewModel#STATE_INVALID} 非法状态，此状态在正常程序中不会出现
//     * <li>
//     *  {@link TimeInventoryViewModel#STATE_DISABLE} 关闭
//     * <li>
//     *  {@link TimeInventoryViewModel#STATE_UNUSED} 未约
//     * <li>
//     *  {@link TimeInventoryViewModel#STATE_USED} 已约
//     * <li>
//     *  {@link TimeInventoryViewModel#STATE_USED_MILESTONE} 已约-里程碑
//     * </ul>
//     *
//     * @param inventories 时间库存对象列表
//     * @return 时间库存视图对象列表
//     * @see TimeInventoryModel
//     * @see TimeInventoryViewModel
//     */
//    List<TimeInventoryViewModel> convert(List<TimeInventoryModel> inventories);
//
//    /**
//     * 根据锁定策略，加工时间库存对象列表
//     *
//     * 支持同一领域对象连续若干日的库存转换
//     * 要求时间库存对象列表中对象描述的日期{@link TimeInventoryModel#date}递增
//     * 要求时间库存对象列表中对象描述的领域对象({@link TimeInventoryModel#scope}, {@link TimeInventoryModel#scopeId}) 一致
//     * 要求时间库存对象列表中对象描述的时间粒度{@link TimeInventoryModel#unit} 一致
//     *
//     * 要求锁参数描述的策略{@link TimeInventoryLockParam#policy}存在
//     *
//     * 可选项：
//     * <ul>
//     * <li>{@link TimeInventoryLockParam#convertUMS} 表述是否需要加工【已约-里程碑】状态
//     * <li>{@link TimeInventoryLockParam#convertWithRelatedInventories} 表述策略加工时是否需要参考该领域对象其它的时间库存
//     * </ul>
//     *
//     * <b>出参</b>
//     * 1. 与 inventories 的时间跨度一致
//     *
//     * 2. 由于 "已约" 以及 "关闭" 状态的存在，会对相邻的时间库存项产生影响。
//     *    例如: 现有2018-04-11 14:00:00 为关闭状态(时间粒度为半小时)，则由于一个服务(假设服务时长为2小时)，
//     *         则 13:30 13:00 12:30 都是由于这个 "关闭" 状态，将被加工成 "关闭" 状态(不改数据库里的状态)
//     * @param inventories 时间库存对象列表
//     * @param lockParam 锁参数
//     * @return 时间库存视图对象列表
//     * @see TimeInventoryModel
//     * @see TimeInventoryLockParam
//     * @see TimeInventoryViewModel
//     */
//    List<TimeInventoryViewModel> convert(List<TimeInventoryModel> inventories, TimeInventoryLockParam lockParam);
//
//    /**
//     * 根据锁策略，加工时间库存对象列表(临时支持-时间库存一期)
//     *
//     * 新增状态描述 {@link TimeInventoryViewModel#STATE_AFFECTED} 被影响
//     * 此状态在 flag = true 时启用，描述由于【已约】状态以及锁策略导致的状态被影响情况。
//     * @param inventories
//     * @param lockParam
//     * @param flag
//     * @return 时间库存视图对象列表
//     * @see TimeInventoryViewConverter#convert(List, TimeInventoryLockParam)
//     */
//    List<TimeInventoryViewModel> convert(List<TimeInventoryModel> inventories, TimeInventoryLockParam lockParam, boolean flag);
//
//}
