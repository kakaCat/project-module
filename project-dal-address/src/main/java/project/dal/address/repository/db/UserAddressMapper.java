package project.dal.address.repository.db;


import org.apache.ibatis.annotations.Param;
import project.dal.address.entity.UserAddress;

import java.util.Date;
import java.util.List;

/**
 * 用户（手艺人和消费者）地址相关DAO层
 * 涉及数据库表
 * @author dangdang@helijia.com
 *
 */
public interface UserAddressMapper {
	
	void insertUserAddress(UserAddress userAddress);
	
	void deleteUserAddress(@Param("userId") String userId,
                           @Param("userAddressId") Long userAddressId);

	void updateUserAddress(UserAddress userAddress);

	UserAddress queryUserAddressById(Long userAddressId);

	List<UserAddress> queryUserAddressListByUpdateDate(String yyyyMMdd);

	List<UserAddress> queryUserAddresses(List<Long> userAddressIds);

	/**
	 * 未删除地址：is_availability = 1 & is_del = 0
	 * @param userId
	 * @return
	 */
	List<UserAddress> queryUserAddressesOfUser(@Param("userId") String userId);

	/**
	 * 根据用户ID与地址ID查询对象，未删除：is_availability = 1 & is_del = 0
	 * @param userId
	 * @param userAddressId
	 * @return
	 */
	UserAddress queryUserAddressOfUserById(@Param("userId") String userId,
                                           @Param("userAddressId") Long userAddressId);

	int queryUserAddressCountOfUser(@Param("userId") String userId);

	/**
	 * 将用户的所有地址都设置为非默认地址
	 * @param userId
	 */
	void updateClearUserAddressDefalut(@Param("userId") String userId);

	/**
	 * 设置默认地址
	 * @param userId
	 * @param userAddressId
	 */
	void updateSetUserAddressDefalut(@Param("userId") String userId,
                                     @Param("userAddressId") Long userAddressId);

	Date queryOldestUpdateDate();

	Long queryProductAddressId(String productId, Long addressId);

//	ArtisanLocation queryArtsianLocation(String artisanId);
//
//	ProductAddress queryProductAddress(@Param("productId") String productId,
//                                       @Param("addressId") Long addressId);

//	List<ProductAddress> queryProductAddresses(@Param("productId") String productId);

	/**
	 * 插入ProductAddress对象，插入成功以后将会把生成的主键ID赋值到对象
	 * @param productAddress
	 */
//	void insertProductAddress(ProductAddress productAddress);

	int updateProductAddressStatus(@Param("addressId") Long addressId,
                                   @Param("status") int status);
	
	int deleteProductAddress(@Param("productId") String productId);
}
