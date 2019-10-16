package project.dal.inventory.time.repository.db;


import org.apache.ibatis.annotations.Param;
import project.dal.inventory.time.entity.TimeInventoryOpModel;
import project.dal.inventory.time.query.TimeInventoryOpQuery;

import java.util.Date;
import java.util.List;

/**
 * @author fangfeng
 * @since 2018/4/11
 */
public interface TimeInventoryOpMapper {

    List<TimeInventoryOpModel> listTimeInventoryOps(TimeInventoryOpQuery opQuery);

    int countTimeInventoryOps(TimeInventoryOpQuery opQuery);

    /**
     * 仅为 DisableWholeDay & EnableWholeDay 提供的基础支撑
     * @param scope
     * @param scopeId
     * @param date
     * @return
     */
    TimeInventoryOpModel getLastTimeInventoryOpByScopeAndDate(@Param("scope") int scope, @Param("scopeId") String scopeId,
                                                              @Param("date") Date date);

    int insertTimeInventoryOp(TimeInventoryOpModel inventoryOp);
}
