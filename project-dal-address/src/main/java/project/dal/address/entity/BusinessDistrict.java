package project.dal.address.entity;


import lombok.Data;

@Data
public class BusinessDistrict extends BaseModel {


	
	/**
	 * 该商圈是否启用 0:未启用;1:启用
	 */
	private boolean isUsed;
	
	private String name;
	
	/**
	 * 行政区划所属市辖区。
	 * TODO 最好对应city表的cityCode字段
	 */
	private String district;
	
	/**
	 * 围成商圈的点（经纬度坐标）的集合。
	 * 比如：116448681,39994026;116476564,39977220;116468659,39971747;116431001,39972411
	 * TODO 这里为什么使用这种方式来存储，是不是有重构的必要
	 */
	private String points;


	private String cityCode;


	private int sort = 0;
	
	/**
	 * 获取商圈的坐标（经纬度）集合
	 * @return
	 */
//	public Point[] getPointArray() {
//		String points = this.getPoints();
//        if (StringUtils.isNotBlank(points)) {
//            List<Point> pointList = new ArrayList<Point>();
//            for (String pointStr : points.split(";")) {
//                Double x = Double.parseDouble(pointStr.split(",")[0]);
//                Double y = Double.parseDouble(pointStr.split(",")[1]);
//                pointList.add(new Point(x.intValue(),y.intValue()));
//            }
//            return pointList.toArray(new Point[pointList.size()]);
//        }
//        return new Point[]{};
//	}


	
	
}
