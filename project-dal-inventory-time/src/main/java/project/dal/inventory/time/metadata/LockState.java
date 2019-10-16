package project.dal.inventory.time.metadata;

public enum LockState {
    STATE_ON(1,"有效"), STATE_OFF(0, "无效");

    private LockState(int state,String name){
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
}