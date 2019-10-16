package project.dal.inventory.time.metadata;

public enum OpType {

    OP_SOURCE_ARTISAN(1,"手艺人"), OP_SOURCE_ORDER(2, "订单"), OP_SOURCE_CUSTOMER_SERVICE(3, "客服"),
    OP_SOURCE_SYSTEM(4,"系统"), OP_SOURCE_SHIFT(5, "排班"), OP_SOURCE_MANAGER(6, "管理员");
    private OpType(int state,String name){
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
