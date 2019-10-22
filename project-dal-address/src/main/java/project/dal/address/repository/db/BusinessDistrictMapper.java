package project.dal.address.repository.db;


import org.apache.ibatis.annotations.Param;
import project.dal.address.entity.BusinessDistrict;

import java.util.Date;
import java.util.List;

/**
 * 商圈相关业务数据表操作，涉及table：
 * business_district 商圈表
 * business_district_artisan 商圈与手艺人关联表
 * business_district_user 商圈与用户关联表
 * bdu_refresh_log 表business_district_user的维护刷新日志
 * us_order_business_district 订单与商圈的关联表
 * 
 * @author dangdang@helijia.com
 *
 */

public interface BusinessDistrictMapper {
	/**
	 * 根据商圈名称获取商圈id
	 * @author lanmao
	 * @date 2017-11-14
	 * @param bdName
	 * @return
	 */
	List<Long> getBusinessDistrictIdByName(@Param("bdName") String bdName);
	
	List<BusinessDistrict> queryAllBusinessDistricts();
	
	List<BusinessDistrict> queryBusinessDistrictsByCity(String cityCode);

	List<BusinessDistrict> queryBusinessDistricts(List<Long> businessDistrictIds);


//	BusinessDistrictUser queryBusinessDistrictUser(Long businessDistrictId, String userId);
//
//	void insertBusinessDistrictUser(BusinessDistrictUser businessDistrictUser);
//
//	void updateBusinessDistrictUser(BusinessDistrictUser businessDistrictUser);
	
	void updateClearContainCommonAddress(@Param("userId") String userId);
	
	List<Long> queryBusinessDistrictIdsOfUser(String userId);
	
	/**
	 * 查询包含了用户常用地址的商圈ID
	 * @param userId
	 * @return
	 */
	List<Long> queryBusinessDistrictIdsContainCommonAddress(@Param("userId") String userId);
	
	Date queryOldestBDURefreshDate();
	
	Integer hasRefreshedDate(String yyyyMMdd);
	
	void insertBDURefreshLog(Date refreshDate);
	
	void insertUsOrderBusinessDistrict(@Param("orderSeq") String orderSeq,
                                       @Param("businessDistrictIds") List<Long> businessDistrictIds);
}
