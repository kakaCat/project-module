package project.dal.inventory.time.metadata;

public enum TimeUnitState {

    UNIT_FIVE(0,192,5),UNIT_QUARTER(1,96,15), UNIT_HALF(2,48, 30), UNIT_FULL(3,24, 60);

    private TimeUnitState(int state,int length, int unitValue){
        this.state = state;
        this.length = length;
        this.unitValue = unitValue;
    }
    private int state;
    private int length;
    private int unitValue;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(int unitValue) {
        this.unitValue = unitValue;
    }

    public static TimeUnitState getTimeUnitState(int state) {
        TimeUnitState[] arrays = values();
        for (TimeUnitState type : arrays) {
            if (type.getState() == state) {
                return type;
            }
        }
        return null;
    }

}
