package project.dal.inventory.time.api;

import project.dal.inventory.time.entity.TimeInventoryLockModel;
import project.dal.inventory.time.entity.TimeInventoryModel;
import project.dal.inventory.time.param.DalParam;
import project.dal.inventory.time.param.TimeInventoryEnableParam;
import project.dal.inventory.time.param.TimeInventoryLockParam;
import project.dal.inventory.time.param.TimeInventoryReleaseParam;

import java.util.List;


/**
 *
 * @author jinli Mar 3, 2018
 */
public interface TimeInventoryService {

    /**
     * 创建时间库存
     * <p>创建时间库存, 并返回创建的结果 {@code List<TimeInventoryModel>}</p>
     * <p>需要注意, 创建参数中指定的日期前闭后开: [param.startDate, param.endDate)</p>
     * @param param 创建库存的参数
     * @return 创建的时间库存列表
     */
    List<TimeInventoryModel> createTimeInventories(DalParam param);

    /**
     * 获取一组时间库存
     * <p>查询时间库存, 并返回查询的结果 {@code List<TimeInventoryModel>}</p>
     * <p>需要注意, 查询参数中指定的日期前闭后开: [param.startDate, param.endDate)</p>
     * @param param 查询库存的参数
     * @return 时间库存列表，往往是N天的所有库存信息
     */
    List<TimeInventoryModel> listTimeInventories(DalParam param);





    /**
     * 将关闭的时间库存开启
     * <p>
     * {@link TimeInventoryEnableParam#items} 最好通过下列操作获得:
     * 1. {@link TimeInventoryService#listTimeInventories(TimeInventoryListParam)} 获得 {@code List<TimeInventoryModel>}
     * 2. {@link TimeInventoryViewConverter} 将 {@code List<TimeInventoryModel>} 转换成 {@code TimeInventoryViewModel}
     * 3. 从 {@link TimeInventoryViewModel#items} 中选择需要操作的 {@code items}
     * </p>
     *
     * <p>
     * 如果选择自行构造 {@link TimeInventoryEnableParam#items}，请一定保证
     *      {@link com.helijia.pivot.inventory.model.TimeInventoryItemModel#date}
     *      {@link com.helijia.pivot.inventory.model.TimeInventoryItemModel#position}
     * 两个参数填写正确且所指代的时间库存单元一致
     * 其中:
     * 1. position 从0开始编号
     * 2. date 精确到时分秒, 需保证date的取值与时间粒度的描述相匹配
     * 例如:
     *  针对某领域实体的2018-05-18的时间库存(时间粒度为30分钟), {@code item} 可以是
     *  {@code TimeInventoryItemModel(0, parseDateTime("2018-05-18 00:00:00")}
     *  {@code TimeInventoryItemModel(47, parseDateTime("2018-05-18 23:30:00")}
     *  但不可以是
     *  {@code TimeInventoryItemModel(0, parseDateTime("2018-05-18 00:00:59")}
     *  {@code TimeInventoryItemModel(2, parseDateTime("2018-05-18 01:15:00")}
     * </p>
     *
     * <p>
     * {@link TimeInventoryEnableParam#items}
     * {@link TimeInventoryDisableParam#items}
     * {@link TimeInventoryLockParam#items}
     * {@link TimeInventoryReleaseParam#items}
     * 若无特别说明，则要求相同
     * </p>
     * 要删除
     */
//    void enableTimeInventories(TimeInventoryEnableParam param);

    /**
     * 将开启的时间库存关闭
     */
//    void disableTimeInventories(TimeInventoryDisableParam param);

    /**
     * 锁定时间库存
     *
     * {@link TimeInventoryLockParam#lockScope}
     * {@link TimeInventoryLockParam#lockScopeId}
     * 将根据不同的上层业务调用，决定是否设置。其它参数必填
     *
     * - 选填场景(以约手艺人时间为例):
     *   1. 买约分离订单(订单号先于锁时间)
     *
     * - 不填写的场景:
     *   1. 先预约时间后产生订单的场景
     *      <b>但是随后必须调用 {@link TimeInventoryLockService#updateTimeInventoryLock(TimeInventoryLockModel)}</b>
     *
     * @param param
     * @return
     */
    TimeInventoryLockModel lockTimeInventories(TimeInventoryLockParam param);

    /**
     * 锁定时间库存
     *
     * - 场景
     *   1. 订单需锁定多种资源的情况(邻里店 手艺人+床位等)
     *
     * @param params
     * @return
     */
    List<TimeInventoryLockModel> lockTimeInventories(List<TimeInventoryLockParam> params);

    /**
     * 释放时间库存
     *
     * <p>
     * 取消服务锁定的时间区间[startTime, endTime], 没有对当前时间的限制
     * 对于场景 startTime < new Date() 的情况，需要上层调用方自行保证(一般仅允许超时未服务订单来进行释放)
     *
     * <p>适用场景:
     *      1. 针对锁定时间的方法的逆过程(items 必须为空)
     *      2. 针对邻里店等可释放部分服务时间(指定scope, scopeId, items)
     *
     * <p>未来扩展(目前仅支持邻里店)
     *      1. 针对5星手艺人等提前结束服务等, 需要将部分时间重置为可约
     */
    void releaseTimeInventories(TimeInventoryReleaseParam param);

    /**
     * 释放时间库存
     *
     * <p>适用场景:
     *      1. 针对同一个lockScope解锁多个scope的情况
     * </p>
     * @param params
     */
    void releaseTimeInventories(List<TimeInventoryReleaseParam> params);
}
