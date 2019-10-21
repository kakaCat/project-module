package order.metadata;

public enum OrderChannel {


    SHOP(1,'S',"店里"),ONLINE(2,'O',"线上");

    private OrderChannel(int index,char pre,String name){
        this.index = index;
        this.pre = pre;
        this.name = name;
    }

    private int index;
    private char pre;
    private String name;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public char getPre() {
        return pre;
    }

    public void setPre(char pre) {
        this.pre = pre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
