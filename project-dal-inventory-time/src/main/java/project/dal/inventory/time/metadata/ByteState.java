package project.dal.inventory.time.metadata;

public enum ByteState {



    STATE_INVALID(0b00,"无效"), STATE_DISABLED(0b01, "关闭"), STATE_UNUSED(0b10, "未约"), STATE_USED(0b11, "已约");

    private ByteState(int state,String name){
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
