package project.dal.address.repository.db;


import project.dal.address.entity.StudioBdRelation;

import java.util.List;

public interface StudioBdRelationMapper {
    //根据商圈id查询小聚点 --查询商圈内的小聚点信息
    List<StudioBdRelation> selectByBdIds(List<Integer> ids);
    
    
    
}