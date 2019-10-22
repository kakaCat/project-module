package project.dal.address.repository.db;


import org.apache.ibatis.annotations.Param;
import project.dal.address.entity.BusinessDistrictArtisanHistory;

import java.util.List;


public interface BusinessDistrictArtisanHistoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(BusinessDistrictArtisanHistory record);

    int insertSelective(BusinessDistrictArtisanHistory record);


    BusinessDistrictArtisanHistory selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(BusinessDistrictArtisanHistory record);

    int updateByPrimaryKey(BusinessDistrictArtisanHistory record);

    /**
     * 根据手艺人和商圈id查修改商圈的历史记录
     * @author lanmao
     * @date 2017-11-14
     * @param artisanId
     * @param bdIds
     * @return
     */
    List<BusinessDistrictArtisanHistory> getBusinessDistrictUpdateHistoryByArtisanId(@Param("artisanId") String artisanId, @Param("bdIds") List<Long> bdIds);
}