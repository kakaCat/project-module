/**
 * 
 */
package project.dal.inventory.time.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.dal.inventory.time.entity.TimeInventoryItemModel;
import project.dal.inventory.time.entity.TimeInventoryViewModel;
import project.dal.inventory.time.metadata.ByteState;
import project.dal.inventory.time.temp.*;

import java.util.*;


public class DateStockServiceUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateStockServiceUtil.class);	
//	/**
//	 *  失效时间,返回已过期
//	 * @param artisanDateStockDetails 时间库存详情
//	 * @param reqDate 当前时间
//     * @return
//     */
	public static List<ArtisanDateStockDetailVo> processExpiredTime(List<ArtisanDateStockDetailVo> artisanDateStockDetails, Date reqDate) {
		for (ArtisanDateStockDetailVo artisanDateStockDetail : artisanDateStockDetails) {
			if (artisanDateStockDetail.getDate().before(DateUtil.getNextHourDate(reqDate, StockConstant.ARTISAN_DATE_CONNOT_APPOINTMENT_HOUR))) {
				artisanDateStockDetail.setState(StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_3);
			}
		}
		return artisanDateStockDetails;
	}
//
//	public static List<ArtisanDateStockDetailVo> processExpiredTime(List<ArtisanDateStockDetailVo> artisanDateStockDetails, Date reqDate, Integer cannotLockMMDayMinute) {
//		if(cannotLockMMDayMinute == null) {
//			processExpiredTime(artisanDateStockDetails, reqDate);
//		}
//		for (ArtisanDateStockDetailVo artisanDateStockDetail : artisanDateStockDetails) {
//			if (artisanDateStockDetail.getDate().before(DateUtil.getNextMinuteDate(reqDate, cannotLockMMDayMinute))) {
//				artisanDateStockDetail.setState(StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_3);
//			}
//		}
//		return artisanDateStockDetails;
//	}
//
	public static String getArtisanDateStockSummaryStatus(List<ArtisanDateStockDetailVo> artisanDateStockDetails) {
		boolean reserved = false;			// 是否被预约了
		for (ArtisanDateStockDetailVo artisanDateStockDetail : artisanDateStockDetails) {
			if (StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_1.equals(artisanDateStockDetail.getState())) {
				return StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_1;
			}
			if (StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_2.equals(artisanDateStockDetail.getState())){
				reserved = true;
			}
		}
		// 全天都没有可约时间：暂不可约/已约满
		if (reserved) {
			return StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_2;
		}
		return StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_0;
	}

	public static List<String> getTimesStatus(List<ArtisanDateStockDetailVo> details) {
        List<String> status = new ArrayList<>(48);
        for (ArtisanDateStockDetailVo detail : details) {
            status.add(detail.getState());
        }
        return status;
    }

	public static String listToString(List<String> status) {
		StringBuilder sb = new StringBuilder();
		for (String s : status) {
			sb.append(s);
		}
		return sb.toString();
	}

	public static Map<Date, ArtisanDate> getArtisanDateMap(List<ArtisanDate> artisanDateList) {
		Map<Date, ArtisanDate> result = new LinkedHashMap<Date, ArtisanDate>();
		if(artisanDateList != null && artisanDateList.size() > 0) {
			for(ArtisanDate artisanDate : artisanDateList) {
				if(artisanDate == null){
                    continue;
                }
				result.put(artisanDate.getSetDate(), artisanDate);
			}
		}
		return result;
	}

	/**
	 * 获取手艺人某天的时间信息
	 * @param artisanDateDetailedList
	 * @return
	 */
	public static Map<Date, List<ArtisanDateDetailed>> getArtisanDateForSetDate(List<ArtisanDateDetailed> artisanDateDetailedList) {
		Map<Date, List<ArtisanDateDetailed>> result = new LinkedHashMap<Date, List<ArtisanDateDetailed>>();
		if(artisanDateDetailedList != null && artisanDateDetailedList.size() > 0) {
			for(ArtisanDateDetailed detailed : artisanDateDetailedList) {
				Date setDate = detailed.getSetDate();
				List<ArtisanDateDetailed> detaileds = result.get(setDate);
				if(detaileds == null) {
					detaileds = new ArrayList<ArtisanDateDetailed>();
					result.put(setDate, detaileds);
				}
				detaileds.add(detailed);
			}
		}
		return result;
	}

	public static List<ArtisanDateStockDetailVo> getArtisanDateStockDetail(ArtisanDateStockVo stock) {
		List<ArtisanDateDetailed> detaileds = stock.getArtisanDateDetailed();
		List<ArtisanDateStockDetailVo> artisanDateStockDetail;
		if (stock.getArtisanDate() == null) {
			artisanDateStockDetail = getDefaultArtisanStockDetail(stock.getDate(), StockConstant.defaultRestDateStr());
		} else if (StockConstant.ARTISAN_DATE_ISRESTALLDAY_YES.equals(stock.getArtisanDate().getIsRestAllDay())) {
			artisanDateStockDetail = getDefaultArtisanStockDetail(stock.getDate(), StockConstant.getAllDateStr());
		} else if (detaileds == null || detaileds.isEmpty()){
			// 有artisanDate,但是没有detail数据，默认为全天休息
			artisanDateStockDetail = getDefaultArtisanStockDetail(stock.getDate(), StockConstant.getAllDateStr());
			logger.error(String.format("artisanDate[%d]不为空，但detailed为空", stock.getArtisanDate().getId()));
		} else {
			artisanDateStockDetail = getArtisanDateStockDetail(detaileds);
		}
		return artisanDateStockDetail;
	}
//
//	/**
//	 * 生成默认时间库存
//	 *
//	 * @param date 时间天
//	 * @param restDateList 休息时间
//	 * @return
//	 */
	private static List<ArtisanDateStockDetailVo> getDefaultArtisanStockDetail(Date date, List<String> restDateList) {
		List<ArtisanDateStockDetailVo> result = new ArrayList<>();
		List<Date> dateList = DateUtil.getAllDate(StockConstant.ARTISAN_DATE_GRANULARITY, date);
		if(dateList != null && dateList.size() > 0) {
			for(Date d : dateList) {
//				String setTime = DateUtil.formatDate(d, "HH:mm");
				if(!restDateList.contains(d)) {
					result.add(createArtisanDateStockDetailVo(d, StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_1));
				} else {
					result.add(createArtisanDateStockDetailVo(d, StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_0));
				}
			}
		}
		return result;
	}
//
//	/**
//	 * 手艺人库存详情
//	 *
//	 * @param detaileds 库存
//	 * @return
//     */
	private static List<ArtisanDateStockDetailVo> getArtisanDateStockDetail(List<ArtisanDateDetailed> detaileds) {
		List<ArtisanDateStockDetailVo> result = new ArrayList<>();
		for(ArtisanDateDetailed detailed : detaileds) {
			Date date = detailed.getSetTime();
//			String time = DateUtil.formatDate(date, "HH:mm");
			// 显示手艺时间库存：暂不可约、可预约、已约满
			ArtisanDateStockDetailVo vo = new ArtisanDateStockDetailVo();
			vo.setDate(date);
			if( StockConstant.ARTISAN_DATE_DETAILED_LOCKSTATUS_01.equals(detailed.getLockStatus())) {
				vo.setState(StockConstant.getArtisanCustomerDateStockViewStatusMap().get(detailed.getStatus()));
//				vo.setTime(time);
			} else {
				vo.setState(StockConstant.ARTISAN_CUSTOMER_VIEW_STATUS_0);
//				vo.setTime(time);
			}
			result.add(vo);
		}
		return result;
	}
//
//
	private static ArtisanDateStockDetailVo createArtisanDateStockDetailVo(Date d, String status) {
		ArtisanDateStockDetailVo vo = new ArtisanDateStockDetailVo();
		vo.setState(status);
//		vo.setTime(DateUtil.formatDate(d, "HH:mm"));
		vo.setDate(d);
		return vo;
	}
//
	public static List<ArtisanDateStockVo> queryAllArtisanDate(String artisanId, Date startDate, Date endDate, List<TimeInventoryViewModel> viewList) {
        List<ArtisanDateDetailed> artisanDateDetailedList = new ArrayList<>();
        List<ArtisanDate> artisanDateList = new ArrayList<>();
        for(TimeInventoryViewModel timeInventoryViewModel : viewList){
            List<TimeInventoryItemModel> timeItems = timeInventoryViewModel.getItems();
            ArtisanDateAndDetail artisanDateAndDetail = convert2ArtisanDate(artisanId, timeItems);
            artisanDateDetailedList.addAll(artisanDateAndDetail.getArtisanDateDetailedList());
            artisanDateList.add(artisanDateAndDetail.getArtisanDate());
        }
        return getArtisanDateStockVo(artisanDateDetailedList, artisanDateList, startDate, endDate);
    }
//
	/**
	 *添加可约时间文案描述
	 *
	 * @param serviceTime
	 */
	public static void setServiceTimeDesc(ArtisanDateStockSummary serviceTime){
		String dayState = serviceTime.getDays();
		int index = dayState.indexOf("1");
		if (0 == index){
			serviceTime.setDesc(ArtisanServiceTimeEnum.AVALAIBLE_TODAY.getValue());
		}else if (1 == index){
			serviceTime.setDesc(ArtisanServiceTimeEnum.AVALAIBLE_TOMORROW.getValue());
		}else if (2 == index){
			serviceTime.setDesc(ArtisanServiceTimeEnum.AVALAIBLE_THREE_DAYS.getValue());
		}else if (index > 2 && index < 7){
			serviceTime.setDesc(ArtisanServiceTimeEnum.AVALAIBLE_SEVEN_DAYS.getValue());
		}else if (index >= 7 && index < dayState.length()){
			serviceTime.setDesc(ArtisanServiceTimeEnum.AVALAIBLE_THIRTY_DAYS.getValue());
		}else {
			serviceTime.setDesc(ArtisanServiceTimeEnum.UN_AVALAIBLE.getValue());
		}
	}
//
//	public static List<ArtisanDateStockVo> queryAllArtisanDateForShop(String artisanId, Date startDate, Date endDate,
//	        List<ResourceTimeInventoryDayViewModel> viewList) {
//        List<ArtisanDateDetailed> artisanDateDetailedList = new ArrayList<>();
//        List<ArtisanDate> artisanDateList = new ArrayList<>();
//        for(ResourceTimeInventoryDayViewModel timeInventoryViewModel : viewList){
//            List<ResourceTimeInventoryModel> timeItems = timeInventoryViewModel.getResourceTimeInventorys();
//            ArtisanDateAndDetail artisanDateAndDetail = convert2ArtisanDate(artisanId, timeItems);
//            artisanDateDetailedList.addAll(artisanDateAndDetail.getArtisanDateDetailedList());
//            artisanDateList.add(artisanDateAndDetail.getArtisanDate());
//        }
//        return getArtisanDateStockVo(artisanDateDetailedList, artisanDateList, startDate, endDate);
//    }

	public static ArtisanDateStockSummary convety2ArtisanDateStockSummary(List<ArtisanDateStockVo> stocks, Date startDate, Date reqDate, boolean summary) {
	    ArtisanDateStockSummary artisanDateStockSummary = new ArtisanDateStockSummary();
	    List<String> daysStatus = new ArrayList();
        Map<String, List<String>> timesStatus = new LinkedHashMap();

        for(int i = 0; i < stocks.size(); ++i) {
            List<ArtisanDateStockDetailVo> artisanDateStockDetails = DateStockServiceUtil.getArtisanDateStockDetail((ArtisanDateStockVo)stocks.get(i));
//            if (i == 0 && DateUtil.parseYYYYMMDD(startDate).equals(DateUtil.parseYYYYMMDD(new Date()))) {
//                artisanDateStockDetails = DateStockServiceUtil.processExpiredTime(artisanDateStockDetails, reqDate);
//            }

            if (summary) {
                daysStatus.add(DateStockServiceUtil.getArtisanDateStockSummaryStatus(artisanDateStockDetails));
            }

            timesStatus.put("day" + i, DateStockServiceUtil.getTimesStatus(artisanDateStockDetails));
        }

        List<String> times = new ArrayList();
        Iterator i$ = timesStatus.values().iterator();

        while(i$.hasNext()) {
            List<String> list = (List)i$.next();
            times.add(DateStockServiceUtil.listToString(list));
        }

        artisanDateStockSummary.setTimes(times);
        if (summary) {
            artisanDateStockSummary.setDays(DateStockServiceUtil.listToString(daysStatus));
        }

//        artisanDateStockSummary.setDate(DateUtil.formatYYYYMMDD(reqDate));
//		setServiceTimeDesc(artisanDateStockSummary);
        return artisanDateStockSummary;
	}

//	public static ArtisanDateStockSummary convety2ArtisanDateStockSummary(List<ArtisanDateStockVo> stocks, Date startDate, Date reqDate, boolean summary, Integer cannotLockMMDay) {
//	    ArtisanDateStockSummary artisanDateStockSummary = new ArtisanDateStockSummary();
//	    List<String> daysStatus = new ArrayList();
//        Map<String, List<String>> timesStatus = new LinkedHashMap();
//
//        for(int i = 0; i < stocks.size(); ++i) {
//            List<ArtisanDateStockDetailVo> artisanDateStockDetails = DateStockServiceUtil.getArtisanDateStockDetail((ArtisanDateStockVo)stocks.get(i));
//            if (i == 0 && DateUtil.formatDate(startDate, "yyyyMMdd").equals(DateUtil.formatDate(new Date(), "yyyyMMdd"))) {
//                artisanDateStockDetails = DateStockServiceUtil.processExpiredTime(artisanDateStockDetails, reqDate, cannotLockMMDay);
//            }
//
//            if (summary) {
//                daysStatus.add(DateStockServiceUtil.getArtisanDateStockSummaryStatus(artisanDateStockDetails));
//            }
//
//            timesStatus.put("day" + i, DateStockServiceUtil.getTimesStatus(artisanDateStockDetails));
//        }
//
//        List<String> times = new ArrayList();
//        Iterator i$ = timesStatus.values().iterator();
//
//        while(i$.hasNext()) {
//            List<String> list = (List)i$.next();
//            times.add(DateStockServiceUtil.listToString(list));
//        }
//
//        artisanDateStockSummary.setTimes(times);
//        if (summary) {
//            artisanDateStockSummary.setDays(DateStockServiceUtil.listToString(daysStatus));
//        }
//
//        artisanDateStockSummary.setDate(DateUtil.formatDate2ShortString(reqDate));
//		setServiceTimeDesc(artisanDateStockSummary);
//        return artisanDateStockSummary;
//	}
//
	private static List<ArtisanDateStockVo> getArtisanDateStockVo(List<ArtisanDateDetailed> artisanDateDetailedList, List<ArtisanDate> artisanDateList,
	        Date startDate, Date endDate){
	    Map<Date, List<ArtisanDateDetailed>> setTimeMap = DateStockServiceUtil.getArtisanDateForSetDate(artisanDateDetailedList);
        Map<Date, ArtisanDate> artisanDateMap = DateStockServiceUtil.getArtisanDateMap(artisanDateList);
        List<Date> dateList = DateUtil.getDates(startDate, endDate);
        List<ArtisanDateStockVo> stocks = new ArrayList();
        Iterator i$ = dateList.iterator();

        while(i$.hasNext()) {
            Date date = (Date)i$.next();
            List<ArtisanDateDetailed> detaileds = (List)setTimeMap.get(date);
            ArtisanDate artisanDate = (ArtisanDate)artisanDateMap.get(date);
            stocks.add(new ArtisanDateStockVo(date, artisanDate, detaileds));
        }
        return stocks;
	}

	private static <T extends TimeInventoryItemModel> ArtisanDateAndDetail convert2ArtisanDate(String artisanId, List<T> timeInventoryItemModels) {
	    ArtisanDateAndDetail result = new ArtisanDateAndDetail();
	    List<ArtisanDateDetailed> artisanDateDetailedList = new ArrayList<>();
	    ArtisanDate artisanDate = new ArtisanDate();
        List<Integer> disables = new ArrayList<>();
        for(TimeInventoryItemModel timeItem : timeInventoryItemModels){
            ArtisanDateDetailed artisanDateDetailed = new ArtisanDateDetailed();
            artisanDateDetailed.setArtisanId(artisanId);
            artisanDateDetailed.setSetDate(DateUtil.toDate(timeItem.getDate()));
            artisanDateDetailed.setSetTime(timeItem.getDate());
            if(timeItem.getState() == ByteState.STATE_DISABLED.getState()){
                disables.add(timeItem.getState());
                artisanDateDetailed.setLockStatus("00");//手艺人设置锁定
                artisanDateDetailed.setStatus("03");
            } else if(timeItem.getState() == TimeInventoryViewModel.STATE_USED
                    ||timeItem.getState() == TimeInventoryViewModel.STATE_USED_MILESTONE){
                artisanDateDetailed.setLockStatus("01");
                artisanDate.setIsOrder("1");
                artisanDateDetailed.setStatus("02");
            }else if(timeItem.getState() == TimeInventoryViewModel.STATE_UNUSED){
                artisanDateDetailed.setLockStatus("01");
                artisanDateDetailed.setStatus("00");
            }
            artisanDateDetailedList.add(artisanDateDetailed);
        }
        if(disables.size() == 48){
            artisanDate.setIsRestAllDay("1");
        }
        artisanDate.setSetDate(timeInventoryItemModels.get(0).getDate());
        artisanDate.setArtisanId(artisanId);

        result.setArtisanDate(artisanDate);
        result.setArtisanDateDetailedList(artisanDateDetailedList);
        return result;
	}

	public static class ArtisanDateAndDetail{
	    private ArtisanDate artisanDate;
	    private List<ArtisanDateDetailed> artisanDateDetailedList;
        public ArtisanDate getArtisanDate() {
            return artisanDate;
        }
        public void setArtisanDate(ArtisanDate artisanDate) {
            this.artisanDate = artisanDate;
        }
        public List<ArtisanDateDetailed> getArtisanDateDetailedList() {
            return artisanDateDetailedList;
        }
        public void setArtisanDateDetailedList(List<ArtisanDateDetailed> artisanDateDetailedList) {
            this.artisanDateDetailedList = artisanDateDetailedList;
        }
	}
	
}
