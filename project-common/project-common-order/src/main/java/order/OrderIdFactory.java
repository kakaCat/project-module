package order;

import order.metadata.OrderChannel;
import order.metadata.OrderProductType;
import order.utils.SnowflakeIdWorker;

public final class OrderIdFactory {


    public static String getOrderId(OrderChannel channel, OrderProductType orderProductType){
        StringBuffer sb = new StringBuffer(100);
        sb.append(channel.getPre()).append(orderProductType.getPre()).append(SnowflakeIdWorker.genId());

        return sb.toString();
    }




}
