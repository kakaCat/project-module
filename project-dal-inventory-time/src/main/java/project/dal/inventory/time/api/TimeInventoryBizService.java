//package project.dal.inventory.time.api;
//
//
//import java.util.Date;
//import java.util.List;
//
///**
// * <p>
// *     时间库存针对高频度出现的业务场景提供的接口&方法
// * </p>
// * @author fangfeng
// * @since 2018/3/29
// */
//public interface TimeInventoryBizService {
//
//    /**
//     * 锁定新时间库存并释放老时间库存
//     *
//     * <p>
//     * 所有参数必填
//     * 同时需要保证
//     * {@link TimeInventoryLockParam#lockScope} 与 {@link TimeInventoryReleaseParam#lockScope} 一致
//     * {@link TimeInventoryLockParam#lockScopeId} 与 {@link TimeInventoryReleaseParam#lockScopeId} 一致
//     * </p>
//     *
//     * <p>
//     * 先释放老时间库存 - 后锁定新时间库存
//     * 事务机制，发生异常即回滚
//     * </p>
//     *
//     * <h3>适用场景</h3>
//     * <p>
//     *      1. 改约时间(改约次数暂时由二方库控制)
//     * </p>
//     * @param lockParam 锁定新的时间库存
//     * @param releaseParam 解锁前次预约的时间库存
//     */
//    void lockAndReleaseTimeInventories(TimeInventoryLockParam lockParam, TimeInventoryReleaseParam releaseParam);
//
//    /**
//     * 设定全天休息，将特定对象的某日时间库存设置为关闭
//     *
//     * 将指定日期的所有时间单元设置为 {@link TimeInventoryModel#STATE_DISABLED}
//     * 如果需要休息的日期有状态为 {@link TimeInventoryModel#STATE_USED} 的时间单元，则操作失败，抛出异常
//     *
//     * 如果某日时间库存不存在，抛出异常
//     *
//     * 局限：仅能支持以每个自然日为单位的所谓全天的操作，若跨天则无法支持(例如: 夜班)
//     *
//     * HINT: 暂时支持，后期可能会通过【排期】项目操作手艺人的排班情况
//     * @param param
//     */
//    void disableWholeDayTimeInventories(TimeInventoryDisableParam param);
//
//    /**
//     * 设定全天恢复，将特定对象的某日时间库存恢复为原状态
//     *
//     * 需要保证在该对象某日时间库存设定全天休息后，没有额外操作，否则本操作失败，抛出异常
//     *
//     * 局限：仅能支持以每个自然日为单位的所谓全天的操作，若跨天则无法支持(例如: 夜班)
//     *
//     * HINT: 暂时支持，后期可能会通过【排期】项目操作手艺人的排班情况
//     * @param param
//     */
//    boolean enableWholeDayTimeInventories(TimeInventoryEnableParam param);
//
//    /**
//     * 批量查询领域对象列表一段时间内的时间库存
//     *
//     * 目前仅供搜索使用
//     *
//     * @param scope 所属领域
//     * @param scopeIds 领域对象id
//     * @param startDate 开始时间(包含)
//     * @param endDate 结束时间(不包含)
//     * @return 时间库存视图对象列表
//     */
//    List<TimeInventoryViewModel> batchListTimeInventories(int scope, List<String> scopeIds, Date startDate, Date endDate);
//
//    /**
//     * 只提供给了邻里店使用，短暂使用了几个版本就已经废弃了 shop < 1.0.4 ，目前已经确认不再使用
//     *
//     * 暂时再留两个月，后续直接删除 @fangfeng @2018-08-16
//     *
//     * @param scope
//     * @param scopeIds
//     * @param startDate
//     * @param endDate
//     * @param lockParam
//     * @return
//     */
//    @Deprecated
//    List<TimeInventoryViewModel> batchListAndConvertTimeInventories(int scope, List<String> scopeIds, Date startDate, Date endDate, TimeInventoryLockParam lockParam);
//
//    /**
//     * 批量查询领域对象列表一段时间内的时间库存
//     *
//     * 单次数据库查询，无其他 convert 操作
//     * 供邻里店多资源批量查询使用
//     *
//     * @param scope
//     * @param scopeIds
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//    List<TimeInventoryModel> batchList(int scope, List<String> scopeIds, Date startDate, Date endDate);
//
//    /**
//     * 查询指定领域对象的时间库存操作记录 [startDate, endDate)
//     * 支持分页查询
//     * @param opQuery
//     * @return
//     */
//    PagedList<TimeInventoryOpModel> listTimeInventoryOps(TimeInventoryOpQuery opQuery);
//}
