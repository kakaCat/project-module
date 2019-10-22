package project.dal.address.repository.db;


import org.apache.ibatis.annotations.Param;
import project.dal.address.entity.BusinessDistrictArtisan;

import java.util.List;

public interface BusinessDistrictArtisanMapper {

    /**
     * @description: //根据手艺人id查询手艺人开通的商圈id
     *
     * @Param [artisanId]
     * @return java.util.List<java.lang.Long>
     **/
    List<Long> queryBusinessDistrictIdsOfArtisan(String artisanId);

    /**
     * @param artisanId 艺人id
     * @Param type 类型
     * @retrn: a
     * @date: 2018/10/14
     * @Desciption: 根据手艺人id查询手艺人开通的商圈id及其商业圈类型
     */
    List<Long> queryBusinessDistrictIdsOfArtisanByType(@Param("artisanId") String artisanId, @Param("type") int type);


    //删除手艺人开通的商圈
    void deleteBusinessDistrictsOfArtisan(@Param("artisanId") String artisanId, @Param("type") int type);

    //保存手艺人开通的商圈
    void saveBusinessDistrictsOfArtisan(@Param("artisanId") String artisanId,
                                        @Param("businessDistrictIds") List<Long> businessDistrictIds, @Param("type") int type);

    //根据手艺人id查询商圈
    List<BusinessDistrictArtisan> getByArtisanId(@Param("artisanId") String artisanId, @Param("type") int type);

    //根据手艺人id和商圈id查询
    List<BusinessDistrictArtisan> getByArtisanIdAndBDIds(@Param("artisanId") String artisanId, @Param("businessDistrictIdList") List<Long> businessDistrictIdList, @Param("type") int type);

    //根据手艺人ids
    List<BusinessDistrictArtisan> getByArtisanIdsAndType(@Param("artisanIds") List<String> artisanIds, @Param("type") int type);


    int deleteByPrimaryKey(@Param("id") Long id, @Param("type") int type);

    int insert(BusinessDistrictArtisan record);

    int insertSelective(BusinessDistrictArtisan record);

    BusinessDistrictArtisan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessDistrictArtisan record);

    int updateByPrimaryKey(BusinessDistrictArtisan record);

    /**
     * 通过商圈id查该商圈内的手艺人
     * @author lanmao
     * @date 2017-12-19
     * @param bdIds
     * @return
     */
    List<BusinessDistrictArtisan> queryByBdIds(@Param("bdIds") List<Long> bdIds);

    /**
     * 通过手艺人ids 获取所以手艺人商圈有重复
     * @author st
     * @param artisanIds
     * @return
     */
    List<Long> getCheckBizByArtisanIds(@Param("artisanIds") List<String> artisanIds);
}