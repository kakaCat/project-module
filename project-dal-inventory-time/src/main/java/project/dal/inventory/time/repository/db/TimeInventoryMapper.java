package project.dal.inventory.time.repository.db;


import org.apache.ibatis.annotations.Param;
import project.dal.inventory.time.entity.TimeInventoryModel;

import java.util.Date;
import java.util.List;



public interface TimeInventoryMapper {

    /**
     * 新增时间库存记录
     * @param inventory
     * @return 受影响的记录行数
     */
    int insertTimeInventory(TimeInventoryModel inventory);

    /**
     * 批量新增时间库存记录
     * @param inventories
     * @return 受影响的记录行数
     */
    int insertTimeInventories(List<TimeInventoryModel> inventories);

    /**
     * 判断特定时间段内的时间库存记录是否存在
     * @param param
     * @return 某条符合查询条件的记录id 或者 NULL
     */
//    Long testTimeInventoryByDates(TimeInventoryCreateParam param);

    /**
     * 根据id获取时间库存记录
     * @param id
     * @return
     */
    TimeInventoryModel getTimeInventoryById(Long id);

    /**
     * @description: //某段时间情况
     *
     * @Param [scope, scopeId, startDate, endDate]
     * @return java.util.List<project.dal.inventory.time.entity.TimeInventoryModel>
     **/
    List<TimeInventoryModel> queryTimeInventories(@Param("scope") int scope, @Param("scopeId") String scopeId,
                                                  @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 批量获取领域ids 在指定时间段内的实际库存记录
     * @return
     */
    List<TimeInventoryModel> batchGetTimeInventoriesByDates(@Param("scope") int scope, @Param("scopeIds") List<String> scopeIds,
                                                            @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    /**
     * 获取一系列指定日期的时间库存记录
     * @param scope
     * @param scopeId
     * @param dates
     * @return
     */
    List<TimeInventoryModel> getTimeInventoriesBySeparateDates(@Param("scope") int scope, @Param("scopeId") String scopeId, @Param("dates") List<Date> dates);

    /**
     * 更新时间库存中若干UNIT的状态
     * @param inventory
     * @return 受影响的记录行数
     */
    int updateTimeInventory(TimeInventoryModel inventory);

    /**
     * 删除时间库存记录(仅供测试使用)
     * @param id
     * @return
     */
    int deleteTimeInventory(Long id);

}
