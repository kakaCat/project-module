package project.inverntory.utils;

import org.junit.Test;
import project.dal.inventory.time.metadata.ScopeType;
import project.dal.inventory.time.metadata.TimeUnitState;
import project.dal.inventory.time.param.TimeInventoryCreateParam;
import project.dal.inventory.time.policy.TimeInventoryCreatePolicy;
import project.dal.inventory.time.policy.impl.DefaultDayShift;
import project.dal.inventory.time.utils.DateUtil;

public class DefaultDayShiftTest {

    private static final int scope =2147483647;

    private static final String scopeId ="test";


    @Test
    public void testCreate() {
        TimeInventoryCreateParam createParam = new TimeInventoryCreateParam();
        createParam.setScope(ScopeType.SCOPE_DEV.getState());
        createParam.setScopeId("test");
        createParam.setTimeUnit(TimeUnitState.UNIT_HALF.getState());
        createParam.setPolicy(TimeInventoryCreateParam.POLICY_ALL_DISABLED);
        createParam.setStartDate(DateUtil.parseDate("2018-05-01"));
        createParam.setEndDate(DateUtil.parseDate("2018-05-05"));

        DefaultDayShift defaultDayShift = new DefaultDayShift();
        TimeInventoryCreatePolicy.TimeInventoryCreateValues values = defaultDayShift.create(createParam);

        System.out.println(Integer.toBinaryString(values.value0));
        System.out.println(Integer.toBinaryString(values.value0).length());
        System.out.println(Integer.toBinaryString(values.value1));
        System.out.println(Integer.toBinaryString(values.value1).length());
        System.out.println(Integer.toBinaryString(values.value2));
        System.out.println(Integer.toBinaryString(values.value2).length());
        System.out.println(Integer.toBinaryString(values.value3));
        System.out.println(Integer.toBinaryString(values.value4));
        System.out.println(Integer.toBinaryString(values.value5));
        System.out.println(Integer.toBinaryString(values.value6));
        System.out.println(Integer.toBinaryString(values.value7));



        System.out.println(values.value0);
        System.out.println(values.value1);
        System.out.println(values.value2);
        System.out.println(values.value3);
        System.out.println(values.value4);
        System.out.println(values.value5);
        System.out.println(values.value6);
        System.out.println(values.value7);

    }

    private void plaintext (int values){
        String str = Integer.toBinaryString(values);
        int length = str.length();
        StringBuffer sb = new StringBuffer();
        int head = 0,tail=2;
        for (int i = 0; i <length/2 ; i++) {
            str.substring(head, tail);
        }
    }


}