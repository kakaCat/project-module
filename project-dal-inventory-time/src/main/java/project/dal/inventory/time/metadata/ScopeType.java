package project.dal.inventory.time.metadata;

public enum ScopeType {

    SCOPE_DEV(Integer.MAX_VALUE,"开发"), SCOPE_ARTISAN(1, "手艺人"), SCOPE_NEIGHBOR_SHOP_CLERK(2, "邻里店店员"), SCOPE_NEIGHBOR_SHOP_DEVICE(3, "邻里店设备");

    private ScopeType(int state,String name){
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