package project.dal.inventory.time.param;


import project.dal.inventory.time.metadata.ByteState;

public class TimeInventoryEnableParam extends BaseTimeInventoryStateOpParam {

    private static final long serialVersionUID = 1L;

    @Override
    public int state() {
        return ByteState.STATE_UNUSED.getState();
    }


}
