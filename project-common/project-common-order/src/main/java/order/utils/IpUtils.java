package order.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@Slf4j
public final class IpUtils {

    private static String cachedip = null;

    private static String localcachedip = null;

    private static Object syncObject = new Object();

    public IpUtils() {
    }

    public static String getRealIpWithStaticCache() {
        if (cachedip == null) {
            synchronized(syncObject) {
                try {
                    cachedip = getRealIp();
                } catch (SocketException var3) {
                    log.error("", var3);
                    cachedip = "127.0.0.1";
                }
            }

            return cachedip;
        } else {
            return cachedip;
        }
    }

    public static String getLocalRealIpWithStaticCache() {
        if (localcachedip == null) {
            synchronized(syncObject) {
                try {
                    localcachedip = getLoaclRealIp();
                } catch (SocketException var3) {
                    log.error("", var3);
                    localcachedip = "127.0.0.1";
                }
            }

            return localcachedip;
        } else {
            return localcachedip;
        }
    }

    public static void flushIpStaticCache() {
        synchronized(syncObject) {
            cachedip = null;
        }
    }

    public static String getRealIp() throws SocketException {
        String localip = null;
        String netip = null;
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;

        while(netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
            Enumeration address = ni.getInetAddresses();

            while(address.hasMoreElements()) {
                ip = (InetAddress)address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                }

                if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    localip = ip.getHostAddress();
                }
            }
        }

        return netip != null && !"".equals(netip) ? netip : localip;
    }

    public static String getLoaclRealIp() throws SocketException {
        String localip = null;
        String netip = null;
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;

        while(netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
            Enumeration address = ni.getInetAddresses();

            while(address.hasMoreElements()) {
                ip = (InetAddress)address.nextElement();
                if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                }
            }
        }

        return (String)(netip != null && !"".equals(netip) ? netip : localip);
    }

//    public static String getHttpClientRemoteIp(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//
//        return ip == null ? "X" : ip;
//    }

    static {
        try {
            cachedip = getRealIp();
        } catch (SocketException var1) {
            log.error("", var1);
            cachedip = "127.0.0.1";
        }

    }
}
