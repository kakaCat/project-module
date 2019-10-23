package project.inverntory;

import project.dal.inventory.time.metadata.TimeUnitState;

public abstract class BaseDate {
    protected int unit = TimeUnitState.UNIT_FULL.getState();

    protected static final int scope =2147483647;

    protected static final String scopeId ="test";

    private static final int SERVICE_COST_TIME = 120;
}
