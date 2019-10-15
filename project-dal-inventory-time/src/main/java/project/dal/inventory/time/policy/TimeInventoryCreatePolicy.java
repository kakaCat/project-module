package project.dal.inventory.time.policy;

import project.dal.inventory.time.param.DalParam;


public interface TimeInventoryCreatePolicy {

    int name();

    /**
     * 初始化内部类TimeInventoryCreateValues的value0~5(一天的库存状态),
     * 供 TimeInventoryService 的 createTimeInventories 来实际创建每天时间库存的状态
     *
     * <b>WARNING:</b>
     * 需要注意在实现此SPI时，要确保value0~5多余的状态位置为0
     * <b>例如:</b> UNIT 选择为 UNIT_HALF 时，value3~5 全部需要置为0.
     * @param delParam
     * @return
     */
    TimeInventoryCreateValues create(DalParam delParam);

    public static class TimeInventoryCreateValues {

        public int value0;
        public int value1;
        public int value2;
        public int value3;
        public int value4;
        public int value5;
        public int value6;
        public int value7;

    }

}
