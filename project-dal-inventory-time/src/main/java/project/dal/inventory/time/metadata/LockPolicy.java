package project.dal.inventory.time.metadata;

public enum LockPolicy {

    HOME_ORIGIN(1,"上门"), Origin(2, "关闭"), StoreOrigin(3, "未约");

    private LockPolicy(int index,String name){
        this.index = index;
        this.name = name;
    }
    private int index;
    private String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
