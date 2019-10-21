package project.dal.inventory.time.param;


import project.dal.inventory.time.metadata.ByteState;

public class TimeInventoryReleaseParam extends BaseTimeInventoryLockOpParam {



    @Override
    public int state() {
        return ByteState.STATE_UNUSED.getState();
    }


}
