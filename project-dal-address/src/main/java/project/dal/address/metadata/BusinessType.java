package project.dal.address.metadata;

public enum BusinessType{
    INDIVIDUAL(0,"个人商圈"),
    FLAGSHIP(1,"手艺人在旗舰店内的商圈"),
    ;
    private int value;
    private String name;
    BusinessType(int value,String name){
        this.value = value;
        this.name = name;
    }
    public int getValue(){
        return value;
    }
    public String getName(){
        return name;
    }
    public static BusinessType getBusinessType(int value) {
        for (BusinessType businessType : BusinessType.values()) {
            if (businessType.value == value) {
                return businessType;
            }
        }
        throw new IllegalArgumentException("argument error: " + value);
    }



}