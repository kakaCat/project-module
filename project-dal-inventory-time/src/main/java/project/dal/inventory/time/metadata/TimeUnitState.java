package project.dal.inventory.time.metadata;

public enum TimeUnitState {

    UNIT_FIVE(0,"5分钟"),UNIT_QUARTER(1,"15分钟"), UNIT_HALF(2, "30分钟"), UNIT_FULL(2, "1小时");

    private TimeUnitState(int state,String name){
        this.state = state;
        this.name = name;
    }
    private int state;
    private String name;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
