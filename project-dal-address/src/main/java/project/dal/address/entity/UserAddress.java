package project.dal.address.entity;

import lombok.Data;

/**
 * 用户（包括手艺人和消费者）设置自己的地址
 *
 */
@Data
public class UserAddress extends BaseModel{


	/**
	 * 是否默认地址 1默认
	 */
	private String commAddress;
	private String userId;
	private String buyerName;
	private String buyerMobile;
	private Integer province;
	private Integer cityCode;
	private Integer towns;
	private String addressPoi;
	private String address;
	private Long longitude;
	private Long latitude;
	private String createUserId;
	private String updateUserId;
	private String isAvailability;
	private String isDel;
	private String des;
	private String location;
	private String ip;
	private String addressFrom;
	private String userType;
	private String addressAoi;



	
	
}