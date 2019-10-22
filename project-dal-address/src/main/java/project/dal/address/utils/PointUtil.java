package project.dal.address.utils;



import java.math.BigDecimal;

public class PointUtil {

    private static final double EARTH_RADIUS = 6378.137D;

    public PointUtil() {
    }

    public static boolean ptInPolygon(Point[] ptPolygon, Point p) {
        int nCount = ptPolygon.length;
        int nCross = 0;

        for(int i = 0; i < nCount; ++i) {
            Point p1 = ptPolygon[i];
            Point p2 = ptPolygon[(i + 1) % nCount];
            if (p1.y != p2.y && p.y >= Math.min(p1.y, p2.y) && p.y < Math.max(p1.y, p2.y)) {
                double x = (double)(p.y - p1.y) * (double)(p2.x - p1.x) / (double)(p2.y - p1.y) + (double)p1.x;
                if (x > (double)p.x) {
                    ++nCross;
                }
            }
        }

        return nCross % 2 == 1;
    }

    public static Double formatLongitude(String longitudeStr) {
        if (longitudeStr.contains(".")) {
            BigDecimal bd = new BigDecimal(longitudeStr.replaceAll(" ", ""));
            longitudeStr = bd.toPlainString();
        }

        if (longitudeStr.length() > 9) {
            longitudeStr = longitudeStr.substring(0, 9);
        }

        return Double.parseDouble(longitudeStr);
    }

    public static Double formatLatitude(String latitudeStr) {
        if (latitudeStr.contains(".")) {
            BigDecimal bd = new BigDecimal(latitudeStr.replaceAll(" ", ""));
            latitudeStr = bd.toPlainString();
        }

        if (latitudeStr.length() > 8) {
            latitudeStr = latitudeStr.substring(0, 8);
        }

        return Double.parseDouble(latitudeStr);
    }

    public static double getDistance6(double lat1, double lng1, double lat2, double lng2) {
        lat1 /= 1000000.0D;
        lng1 /= 1000000.0D;
        lat2 /= 1000000.0D;
        lng2 /= 1000000.0D;
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));
        s *= 6378.137D;
        s = (double)Math.round(s * 10000.0D) / 10000.0D;
        return s;
    }

    private static double rad(double d) {
        return d * 3.141592653589793D / 180.0D;
    }
}
